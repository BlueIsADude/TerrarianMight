package net.bluethedude.terrarianmight.item.custom;

import net.bluethedude.terrarianmight.enchantments.TerrarianEnchantments;
import net.bluethedude.terrarianmight.entity.custom.PorkChopEntity;
import net.bluethedude.terrarianmight.item.custom.util.AbstractMagicItem;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class PorkStormItem extends AbstractMagicItem {

    public static final int MANA_COST = 1;
    public static final int MAX_MANA = 100;

    public PorkStormItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getManaCost() {
        return MANA_COST;
    }

    @Override
    public int getMaxMana(ItemStack stack) {
        return MAX_MANA;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        tooltip.addLast(Text.empty());
        tooltip.addLast(Text.translatable("tooltip.terrarianmight.magic_item.amethyst_staff").formatted(Formatting.BLUE));

        super.appendTooltip(stack, context, tooltip, type);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 0.0, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, 0.0F, EntityAttributeModifier.Operation.ADD_VALUE),
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
                PorkChopEntity porkchopEntity = new PorkChopEntity(world, user);
                porkchopEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 2.5F, 1.0F);
                world.spawnEntity(porkchopEntity);
            }
            world.playSound(
                    null,
                    playerEntity.getX(),
                    playerEntity.getY(),
                    playerEntity.getZ(),
                    SoundEvents.ENTITY_PIG_AMBIENT,
                    SoundCategory.PLAYERS,
                    1.0F,
                    2.0F / (world.getRandom().nextFloat() * 1.4F + 1.2F)
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
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 1;
    }
}
