package net.bluethedude.terrarianmight.entity.custom.util;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
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

    protected AbstractMagicProjectileEntity(EntityType<? extends AbstractMagicProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    protected AbstractMagicProjectileEntity(EntityType<? extends AbstractMagicProjectileEntity> type, double x, double y, double z, World world) {
        this(type, world);
        this.setPosition(x, y, z);
    }

    protected AbstractMagicProjectileEntity(EntityType<? extends AbstractMagicProjectileEntity> type, LivingEntity owner, World world) {
        this(type, owner.getX(), owner.getEyeY() - 0.1F, owner.getZ(), world);
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

        this.checkBlockCollision();
        Vec3d vec3d = this.getVelocity();
        double d = this.getX() + vec3d.x;
        double e = this.getY() + vec3d.y;
        double f = this.getZ() + vec3d.z;
        this.updateRotation();
        float h = 0.99F;

        this.setVelocity(vec3d.multiply(h));
        ParticleEffect particleEffect = this.getFlyingParticleType();
        World world = this.getWorld();
        if (particleEffect != null) {
            if (!world.isClient) {
                ((ServerWorld) world).spawnParticles(getFlyingParticleType(),
                        getX(),
                        getY(),
                        getZ(),
                        1, 0.04, 0.04, 0.04, 0
                );
            }
        }
        this.ticksSinceFired++;
        if (this.ticksSinceFired % getMaxLifetime() == 0) {
            this.projectileExpire(world);
        }
        this.setPosition(d, e, f);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        World world = this.getWorld();
        this.projectileExpire(world);
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
                this.discard();
            }
        }
    }

    protected int getMaxLifetime() {
        return 38;
    }
}
