package net.bluethedude.terrarianmight.item.custom;

import net.bluethedude.terrarianmight.item.custom.util.AbstractMagicItem;
import net.bluethedude.terrarianmight.sound.TerrarianSoundEvents;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WindChargeEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class ZephyrScepterItem extends AbstractMagicItem {

    public ZephyrScepterItem(int manaCost, int maxMana, Item.Settings settings) {
        super(manaCost, maxMana, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        tooltip.addLast(Text.empty());
        tooltip.addLast(Text.translatable("tooltip.terrarianmight.magic_item.zephyr_scepter").formatted(Formatting.BLUE));

        super.appendTooltip(stack, context, tooltip, type);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 3.0, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -3.2F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity playerEntity) {
            if (!world.isClient) {
                WindChargeEntity windChargeEntity1 = new WindChargeEntity(playerEntity, world, user.getX(), user.getEyeY(), user.getZ());
                shoot(playerEntity, windChargeEntity1, 1.5F, 1.0F);
                world.spawnEntity(windChargeEntity1);

                WindChargeEntity windChargeEntity2 = new WindChargeEntity(playerEntity, world, user.getX(), user.getEyeY(), user.getZ());
                shoot(playerEntity, windChargeEntity2, 1.5F, 1.0F);
                world.spawnEntity(windChargeEntity2);
            }
            world.playSound(
                    null,
                    playerEntity.getX(),
                    playerEntity.getY(),
                    playerEntity.getZ(),
                    TerrarianSoundEvents.ITEM_MAGIC_ITEM_CAST_SPELL,
                    SoundCategory.PLAYERS,
                    1.0F,
                    1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F)
            );
            if (!playerEntity.isCreative()) {
                setMana(stack, getMana(stack) - getManaCost());
            }
            playerEntity.swingHand(playerEntity.getActiveHand());
            playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }
        return stack;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 20;
    }

    @Override
    public int getEnchantability() {
        return 1;
    }
}
