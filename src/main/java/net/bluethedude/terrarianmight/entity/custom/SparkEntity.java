package net.bluethedude.terrarianmight.entity.custom;

import net.bluethedude.terrarianmight.entity.TerrarianEntityTypes;
import net.bluethedude.terrarianmight.entity.custom.util.AbstractMagicProjectileEntity;
import net.bluethedude.terrarianmight.entity.damage.TerrarianDamageTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

public class SparkEntity extends AbstractMagicProjectileEntity {

    public SparkEntity(EntityType<? extends SparkEntity> entityType, World world) {
        super(entityType, world);
    }

    public SparkEntity(World world, LivingEntity owner) {
        super(TerrarianEntityTypes.SPARK, owner, world);
    }

    public SparkEntity(World world, double x, double y, double z) {
        super(TerrarianEntityTypes.SPARK, x, y, z, world);
    }

    @Override
    @Nullable
    protected ParticleEffect getFlyingParticleType() {
        return ParticleTypes.SMALL_FLAME;
    }

    @Override
    @Nullable
    protected ParticleEffect getCrashingParticleType() {
        return ParticleTypes.SMOKE;
    }

    @Override
    public void tick() {
        super.tick();
        World world = this.getWorld();
        if (this.touchingWater || this.inPowderSnow) {
            this.projectileExpire(world);
        }
        this.applyGravity();
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        HitResult.Type type = hitResult.getType();
        if (type == HitResult.Type.ENTITY) {
            EntityHitResult entityHitResult = (EntityHitResult)hitResult;
            Entity entity = entityHitResult.getEntity();
            if (entity.getType().isIn(EntityTypeTags.REDIRECTABLE_PROJECTILE) && entity instanceof ProjectileEntity projectileEntity) {
                projectileEntity.deflect(ProjectileDeflection.REDIRECTED, this.getOwner(), this.getOwner(), true);
            }
            this.onEntityHit(entityHitResult);
            this.getWorld().emitGameEvent(GameEvent.PROJECTILE_LAND, hitResult.getPos(), GameEvent.Emitter.of(this, null));

        } else if (type == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult)hitResult;
            this.onBlockHit(blockHitResult);
            BlockPos blockPos = blockHitResult.getBlockPos();
            this.getWorld().emitGameEvent(GameEvent.PROJECTILE_LAND, blockPos, GameEvent.Emitter.of(this, this.getWorld().getBlockState(blockPos)));
            this.projectileExpire(this.getWorld());
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            Entity entity = entityHitResult.getEntity();
            Entity owner = this.getOwner();
            int i = entity.getFireTicks();
            entity.setOnFireFor(5.0F);
            DamageSource damageSource = this.getDamageSources().create(TerrarianDamageTypes.SPARK_FIRE, this, owner);
            if (!entity.damage(damageSource, 5.0F)) {
                entity.setFireTicks(i);
            } else {
                EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource);
            }
        }
    }

    @Override
    protected void projectileExpire(World world) {
        if (!world.isClient) {
            ParticleEffect particleEffect2 = this.getCrashingParticleType();
            if (particleEffect2 != null) {
                ((ServerWorld) world).spawnParticles(getCrashingParticleType(),
                        getX(),
                        getY(),
                        getZ(),
                        5, 0.02, 0.02, 0.02, 0.0
                );
            }
            this.discard();
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {}

    @Override
    protected double getGravity() {
        return 0.01;
    }
}