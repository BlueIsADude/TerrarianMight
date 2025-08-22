package net.bluethedude.terrarianmight.item.custom.util;

import net.bluethedude.terrarianmight.util.TerrarianDataComponents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
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

public class AbstractSummonItem extends Item {

    public static final int SUMMON_COOLDOWN = 600;
    public static final int MANA_COST = 5;
    public static final int MAX_MANA = 20;
    private static final int ITEM_BAR_COLOR = MathHelper.packRgb(0.4F, 0.4F, 1.0F);

    public AbstractSummonItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.terrarianmight.magic_item.mana", getMana(stack), MAX_MANA).formatted(Formatting.WHITE));

        super.appendTooltip(stack, context, tooltip, type);
    }

    private int getMana(ItemStack stack) {
        if (stack.contains(TerrarianDataComponents.MANA)) {
            return Objects.requireNonNull(stack.get(TerrarianDataComponents.MANA));
        } else {
            return 0;
        }
    }

    private void setMana(ItemStack stack, int mana) {
        stack.set(TerrarianDataComponents.MANA, mana);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getMana(stack) >= 1;

    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return MathHelper.clamp(Math.round(getMana(stack) * 13.0F / MAX_MANA), 0, 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        return ITEM_BAR_COLOR;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (getMana(itemStack) < MANA_COST) {
            return TypedActionResult.fail(itemStack);
        } else {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (getMana(stack) < MAX_MANA) {
            setMana(stack, getMana(stack) + 1);
        }
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity playerEntity) {
            int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
            float f = getPullProgress(i);
            if (!(f < 0.1)) {
                if (!world.isClient) {
                    this.spawnMinion(user.getWorld(), user.getX(), user.getY() + 0.5, user.getZ(), playerEntity);
                }
                world.playSound(
                        null,
                        playerEntity.getX(),
                        playerEntity.getY(),
                        playerEntity.getZ(),
                        SoundEvents.ENTITY_EVOKER_CAST_SPELL,
                        SoundCategory.PLAYERS,
                        1.0F,
                        1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                );
                setMana(stack, getMana(stack) - MANA_COST);
                playerEntity.getItemCooldownManager().set(this, SUMMON_COOLDOWN);
                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            }
        }
    }

    public void spawnMinion(World world, double x, double y, double z, PlayerEntity user) {}

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public static float getPullProgress(int useTicks) {
        float f = useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }
}
