package net.bluethedude.terrarianmight.item.custom.util;

import net.bluethedude.terrarianmight.TerrarianConfig;
import net.bluethedude.terrarianmight.util.TerrarianDataComponents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
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
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;
import java.util.Objects;

public abstract class AbstractMagicItem extends Item {
    private final int manaCost;
    private final int maxMana;

    private static final int ITEM_BAR_COLOR = MathHelper.packRgb(0.4F, 0.4F, 1.0F);

    private int ticksHeld;
    private boolean isBeingUsed;

    public AbstractMagicItem(int manaCost, int maxMana, Item.Settings settings) {
        super(settings);
        this.manaCost = manaCost;
        this.maxMana = maxMana;
    }

    protected int getManaCost() {
        return manaCost;
    }

    protected int getMaxMana() {
        return maxMana;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.addLast(Text.translatable("tooltip.terrarianmight.magic.mana_cost", getManaCost()).formatted(Formatting.RED));
        tooltip.addLast(Text.translatable("tooltip.terrarianmight.magic.mana", getMana(stack), getMaxMana()).formatted(Formatting.WHITE));
        if (stack.hasEnchantments()) {
            tooltip.addLast(Text.empty());
        }

        super.appendTooltip(stack, context, tooltip, type);
    }

    public int getMana(ItemStack stack) {
        if (stack.contains(TerrarianDataComponents.MANA)) {
            return Objects.requireNonNull(stack.get(TerrarianDataComponents.MANA));
        } else {
            return getMaxMana();
        }
    }

    public void setMana(ItemStack stack, int mana) {
        stack.set(TerrarianDataComponents.MANA, mana);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return getMana(stack) < getMaxMana();
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return MathHelper.clamp(Math.round(getMana(stack) * 13.0F / getMaxMana()), 0, 13);
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
        if (getMana(stack) < getMaxMana()) {
            setMana(stack, getMana(stack) + 1);
        }
        return super.postHit(stack, target, attacker);
    }

    protected void shoot(LivingEntity shooter, ProjectileEntity projectile, float speed, float divergence) {
        float yaw = 0.0F;
        Vec3d vec3d = shooter.getOppositeRotationVector(1.0F);
        Quaternionf quaternionf = new Quaternionf().setAngleAxis(yaw * (float) (Math.PI / 180.0), vec3d.x, vec3d.y, vec3d.z);
        Vec3d vec3d2 = shooter.getRotationVec(1.0F);
        Vector3f vector3f = vec3d2.toVector3f().rotate(quaternionf);

        projectile.setVelocity(vector3f.x(), vector3f.y(), vector3f.z(), speed, divergence);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (!world.isClient && user instanceof PlayerEntity && TerrarianConfig.passiveManaRegen) {
            isBeingUsed = true;
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity instanceof PlayerEntity && TerrarianConfig.passiveManaRegen) {
            this.ticksHeld++;
            if ((getMana(stack) < getMaxMana() && selected && this.ticksHeld % TerrarianConfig.manaRegenRate == 0)) {
                if (!isBeingUsed) {
                    setMana(stack, getMana(stack) + 1);
                } else {
                    isBeingUsed = false;
                }
            }
        }
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
