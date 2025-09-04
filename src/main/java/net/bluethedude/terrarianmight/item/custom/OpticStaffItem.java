package net.bluethedude.terrarianmight.item.custom;

import net.bluethedude.terrarianmight.entity.TerrarianEntityTypes;
import net.bluethedude.terrarianmight.entity.custom.RetinazerEntity;
import net.bluethedude.terrarianmight.entity.custom.SpazmatismEntity;
import net.bluethedude.terrarianmight.item.custom.util.AbstractSummonItem;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class OpticStaffItem extends AbstractSummonItem {

    public OpticStaffItem(int manaCost, int maxMana, int summonLifespan, Item.Settings settings) {
        super(manaCost, maxMana, summonLifespan, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {

        tooltip.addLast(Text.empty());
        tooltip.addLast(Text.translatable("tooltip.terrarianmight.summon_item.optic_staff").formatted(Formatting.BLUE));

        super.appendTooltip(stack, context, tooltip, type);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 2.0, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.8F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2);
    }

    @Override
    public void spawnMinion(World world, double x, double y, double z, PlayerEntity user, ItemStack stack) {
        SpazmatismEntity spazmatismEntity = TerrarianEntityTypes.SPAZ.create(world);
        if (spazmatismEntity != null) {
            spazmatismEntity.refreshPositionAndAngles(x, y, z, world.getRandom().nextFloat() * 360.0F, 0.0F);
            spazmatismEntity.setOwner(user);
            spazmatismEntity.maxLifespan = getSummonLifespan();
            world.spawnEntity(spazmatismEntity);
        }
        RetinazerEntity retinazerEntity = TerrarianEntityTypes.REZ.create(world);
        if (retinazerEntity != null) {
            retinazerEntity.refreshPositionAndAngles(x, y, z, world.getRandom().nextFloat() * 360.0F, 0.0F);
            retinazerEntity.setOwner(user);
            retinazerEntity.maxLifespan = getSummonLifespan();
            world.spawnEntity(retinazerEntity);
        }
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
