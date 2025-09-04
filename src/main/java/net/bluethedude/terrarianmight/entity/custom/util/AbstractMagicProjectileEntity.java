package net.bluethedude.terrarianmight.entity.custom.util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractMagicProjectileEntity extends ProjectileEntity {
    private int ticksSinceFired;
    public int maxLifespan = 1;

    protected AbstractMagicProjectileEntity(EntityType<? extends AbstractMagicProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    protected AbstractMagicProjectileEntity(EntityType<? extends AbstractMagicProjectileEntity> type, double x, double y, double z, World world) {
        this(type, world);
        this.setPosition(x, y, z);
    }

    protected AbstractMagicProjectileEntity(EntityType<? extends AbstractMagicProjectileEntity> type, LivingEntity owner, World world) {
        this(type, owner.getX(), owner.getEyeY() - 0.15F, owner.getZ(), world);
        this.setOwner(owner);
    }

    @Override
    public boolean shouldRender(double distance) {
        double d = this.getBoundingBox().getAverageSideLength() * 4.0;
        if (Double.isNaN(d)) {
            d = 4.0;
        }

        d *= 64.0;
        return distance < d * d;
    }

    @Override
    public boolean canUsePortals(boolean allowVehicles) {
        return true;
    }

    @Override
    public void tick() {
        super.tick();
        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        if (hitResult.getType() != HitResult.Type.MISS) {
            this.hitOrDeflect(hitResult);
        }

        Vec3d vec3d = this.getVelocity();
        this.move(MovementType.SELF, vec3d);
        this.setVelocity(vec3d.multiply(0.99));

        this.checkBlockCollision();
        this.updateRotation();

        ParticleEffect particleEffect = this.getFlyingParticleType();
        World world = this.getWorld();
        if (particleEffect != null) {
            if (!world.isClient) {
                ((ServerWorld) world).spawnParticles(getFlyingParticleType(),
                        getX(),
                        getY(),
                        getZ(),
                        1, 0.05, 0.05, 0.05, 0
                );
            }
        }
        this.ticksSinceFired++;
        if (this.ticksSinceFired % getMaxLifespan() == 0) {
            this.projectileExpire(world);
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        this.projectileExpire(this.getWorld());
    }

    @Nullable
    protected ParticleEffect getFlyingParticleType() {
        return null;
    }

    @Nullable
    protected ParticleEffect getCrashingParticleType() {
        return null;
    }

    protected void projectileExpire(World world) {
        if (!world.isClient) {
            ParticleEffect particleEffect2 = this.getCrashingParticleType();
            if (particleEffect2 != null) {
                ((ServerWorld) world).spawnParticles(getCrashingParticleType(),
                        getX(),
                        getY(),
                        getZ(),
                        20, 0, 0, 0, 0.05
                );
            }
            this.discard();
        }
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    protected int getMaxLifespan() {
        return maxLifespan;
    }
}
