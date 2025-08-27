package net.bluethedude.terrarianmight.item.custom;

import net.bluethedude.terrarianmight.enchantments.TerrarianEnchantments;
import net.bluethedude.terrarianmight.entity.TerrarianEntityTypes;
import net.bluethedude.terrarianmight.entity.custom.SlimeWolfEntity;
import net.bluethedude.terrarianmight.item.custom.util.AbstractSummonItem;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class SlimeStaffItem extends AbstractSummonItem {

    public static final int SUMMON_LIFESPAN = 600;
    public static final int MANA_COST = 5;
    public static final int MAX_MANA = 20;

    public SlimeStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getSummonLifespan() {
        return SUMMON_LIFESPAN;
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
        tooltip.addLast(Text.translatable("tooltip.terrarianmight.summon_item.slime_staff").formatted(Formatting.BLUE));

        super.appendTooltip(stack, context, tooltip, type);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 1.0, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -3.0F, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(), 1.0F, 2);
    }

    @Override
    public void spawnMinion(World world, double x, double y, double z, PlayerEntity user, ItemStack stack) {
        SlimeWolfEntity slimeWolfEntity = TerrarianEntityTypes.SLIME_WOLF.create(world);
        if (slimeWolfEntity != null) {
            slimeWolfEntity.refreshPositionAndAngles(x, y, z, world.getRandom().nextFloat() * 360.0F, 0.0F);
            slimeWolfEntity.setOwner(user);
            world.spawnEntity(slimeWolfEntity);
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 16;
    }

    @Override
    public int getEnchantability() {
        return 1;
    }
}
