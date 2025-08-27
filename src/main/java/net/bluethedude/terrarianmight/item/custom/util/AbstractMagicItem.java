package net.bluethedude.terrarianmight.item.custom.util;

import net.bluethedude.terrarianmight.util.TerrarianDataComponents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public abstract class AbstractMagicItem extends Item {

    private static final int ITEM_BAR_COLOR = MathHelper.packRgb(0.4F, 0.4F, 1.0F);

    public AbstractMagicItem(Item.Settings settings) {
        super(settings);
    }

    public int getManaCost() {
        return 1;
    }

    public int getMaxMana(ItemStack stack) {
        return 1;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.addLast(Text.translatable("tooltip.terrarianmight.magic.mana_cost", getManaCost()).formatted(Formatting.RED));
        tooltip.addLast(Text.translatable("tooltip.terrarianmight.magic.mana", getMana(stack), getMaxMana(stack)).formatted(Formatting.WHITE));
        if (stack.hasEnchantments()) {
            tooltip.addLast(Text.empty());
        }

        super.appendTooltip(stack, context, tooltip, type);
    }

    public int getMana(ItemStack stack) {
        if (stack.contains(TerrarianDataComponents.MANA)) {
            return Objects.requireNonNull(stack.get(TerrarianDataComponents.MANA));
        } else {
            return getMaxMana(stack);
        }
    }

    public void setMana(ItemStack stack, int mana) {
        stack.set(TerrarianDataComponents.MANA, mana);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getMana(stack) < getMaxMana(stack);
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return MathHelper.clamp(Math.round(getMana(stack) * 13.0F / getMaxMana(stack)), 0, 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return ITEM_BAR_COLOR;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (getMana(itemStack) < getManaCost() && !user.isCreative()) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (getMana(stack) < getMaxMana(stack)) {
            setMana(stack, getMana(stack) + 1);
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }
}
