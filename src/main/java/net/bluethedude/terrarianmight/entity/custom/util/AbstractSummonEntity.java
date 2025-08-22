package net.bluethedude.terrarianmight.entity.custom.util;

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
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.TimeHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class AbstractSummonEntity extends TameableEntity implements Angerable {

    private static final TrackedData<Integer> ANGER_TIME = DataTracker.registerData(AbstractSummonEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private static final int MAX_LIFETIME = 600;
    private int ticksSinceSpawn;
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
    }

    @Override
    protected void mobTick() {
        this.ticksSinceSpawn++;
        if (this.ticksSinceSpawn % MAX_LIFETIME == 0) {
            this.damage(this.getDamageSources().generic(), this.getHealth());
        }
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
    public boolean canAttackWithOwner(LivingEntity target, LivingEntity owner) {
        if (target instanceof CreeperEntity || target instanceof GhastEntity || target instanceof ArmorStandEntity) {
            return false;
        } else if (target instanceof AbstractSummonEntity summonEntity) {
            return !summonEntity.isTamed() || summonEntity.getOwner() != owner;
        } else if (target instanceof WolfEntity wolfEntity) {
            return !wolfEntity.isTamed() || wolfEntity.getOwner() != owner;
        } else if (target instanceof PlayerEntity playerEntity && owner instanceof PlayerEntity playerEntity2 && !playerEntity2.shouldDamagePlayer(playerEntity)) {
            return false;
        } else {
            return (!(target instanceof AbstractHorseEntity abstractHorseEntity) || !abstractHorseEntity.isTame()) && !(target instanceof TameableEntity tameableEntity && tameableEntity.isTamed());
        }
    }

    @Override
    public void setOwner(PlayerEntity player) {
        this.setTamed(true, true);
        this.setOwnerUuid(player.getUuid());
    }
}
