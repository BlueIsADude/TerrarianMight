package net.bluethedude.terrarianmight.entity.custom.util;

import net.bluethedude.terrarianmight.criterion.TerrarianCriteria;
import net.bluethedude.terrarianmight.sound.TerrarianSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class AbstractSummonEntity extends TameableEntity implements Angerable {

    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(AbstractSummonEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private int ticksSinceSpawn;
    public int maxLifespan = 1;
    private static final UniformIntProvider ANGER_TIME_RANGE = TimeHelper.betweenSeconds(20, 39);
    @Nullable
    private UUID angryAt;

    protected AbstractSummonEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.setTamed(false, false);
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public void onDeath(DamageSource damageSource) {}

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ANGER_TIME, 0);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        this.writeAngerToNbt(nbt);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.readAngerFromNbt(this.getWorld(), nbt);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (!this.getWorld().isClient) {
            this.tickAngerLogic((ServerWorld)this.getWorld(), true);
        }
        if (!this.getWorld().isClient && this.isAlive() && this.age % 10 == 0) {
            this.heal(1.0F);
        }
    }

    @Override
    protected void mobTick() {
        if (this.isTamed()) {
            this.ticksSinceSpawn++;
            if (this.ticksSinceSpawn % maxLifespan == 0 || this.getOwner() != null && this.getOwner().isDead()) {
                this.vanish();
            }
        }
    }

    protected void vanish() {
        World world = this.getWorld();
        this.playSound(TerrarianSoundEvents.ITEM_MAGIC_ITEM_SPELL_VANISH, 1.0F, 1.0F);
        ((ServerWorld) world).spawnParticles(ParticleTypes.PORTAL,
                this.getX(),
                this.getY(),
                this.getZ(),
                10, 0.3, 0.3, 0.3, 0.1
        );
        this.discard();
    }

    @Override
    public boolean canBeLeashed() {
        return false;
    }

    @Override
    public int getAngerTime() {
        return this.dataTracker.get(ANGER_TIME);
    }

    @Override
    public void setAngerTime(int angerTime) {
        this.dataTracker.set(ANGER_TIME, angerTime);
    }

    @Override
    public void chooseRandomAngerTime() {
        this.setAngerTime(ANGER_TIME_RANGE.get(this.random));
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return this.angryAt;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ActionResult actionResult = super.interactMob(player, hand);
        if (!this.getWorld().isClient) {
            if (this.isTamed() && this.getAngerTime() != 0 && this.isOwner(player)) {
                this.setAngerTime(0);
                player.sendMessage(Text.translatable("alert.terrarianmight.calmed_down", this.getName()).formatted(Formatting.WHITE), true);
                return ActionResult.SUCCESS_NO_ITEM_USED;
            }
        }
        return actionResult;
    }

    @Override
    public boolean canAttackWithOwner(LivingEntity target, LivingEntity owner) {
        if (target instanceof CreeperEntity || target instanceof GhastEntity || target instanceof ArmorStandEntity) {
            return false;
        } else if (target instanceof AbstractSummonEntity summonEntity) {
            return !summonEntity.isTamed() || summonEntity.getOwner() != owner;
        } else {
            if (target instanceof WolfEntity wolfEntity) {
                return !wolfEntity.isTamed() || wolfEntity.getOwner() != owner;
            } else if (target instanceof PlayerEntity playerEntity && owner instanceof PlayerEntity playerEntity2 && !playerEntity2.shouldDamagePlayer(playerEntity)) {
                return false;
            } else {
                return (!(target instanceof AbstractHorseEntity abstractHorseEntity) || !abstractHorseEntity.isTame()) && !(target instanceof TameableEntity tameableEntity && tameableEntity.isTamed());
            }
        }
    }

    @Override
    public void setOwner(PlayerEntity player) {
        this.setTamed(true, false);
        this.setOwnerUuid(player.getUuid());
        if (player instanceof ServerPlayerEntity serverPlayerEntity) {
            TerrarianCriteria.SUMMON_MINION.trigger(serverPlayerEntity, this);
        }
    }
}
