package net.bluethedude.terrarianmight.item.custom;

import net.bluethedude.terrarianmight.entity.TerrarianEntityTypes;
import net.bluethedude.terrarianmight.entity.custom.SlimeWolfEntity;
import net.bluethedude.terrarianmight.item.custom.util.AbstractSummonItem;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class SlimeStaffItem extends AbstractSummonItem {

    public SlimeStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.empty());
        tooltip.add(Text.translatable("tooltip.terrarianmight.item.when_used").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("tooltip.terrarianmight.summon_item.slime_staff").formatted(Formatting.DARK_PURPLE));
        tooltip.add(Text.translatable("tooltip.terrarianmight.magic_item.mana_cost", AbstractSummonItem.MANA_COST).formatted(Formatting.RED));

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
    public void spawnMinion(World world, double x, double y, double z, PlayerEntity user) {
        SlimeWolfEntity slimeWolfEntity = TerrarianEntityTypes.SLIME_WOLF.create(world);
        if (slimeWolfEntity != null) {
            slimeWolfEntity.refreshPositionAndAngles(x, y, z, world.getRandom().nextFloat() * 360.0F, 0.0F);
            slimeWolfEntity.setOwner(user);
            world.spawnEntity(slimeWolfEntity);
        }
    }

    @Override
    public int getEnchantability() {
        return 1;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.SLIME_BALL);
    }
}
