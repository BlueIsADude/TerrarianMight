package net.bluethedude.terrarianmight.entity.custom;

import net.bluethedude.terrarianmight.entity.TerrarianEntityTypes;
import net.bluethedude.terrarianmight.entity.custom.util.AbstractMagicProjectileEntity;
import net.bluethedude.terrarianmight.particle.TerrarianParticleTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class EndLaserEntity extends AbstractMagicProjectileEntity {

    public EndLaserEntity(EntityType<? extends EndLaserEntity> entityType, World world) {
        super(entityType, world);
    }

    public EndLaserEntity(World world, LivingEntity owner) {
        super(TerrarianEntityTypes.END_LASER, owner, world);
    }

    public EndLaserEntity(World world, double x, double y, double z) {
        super(TerrarianEntityTypes.END_LASER, x, y, z, world);
    }

    @Override
    @Nullable
    protected ParticleEffect getCrashingParticleType() {
        return TerrarianParticleTypes.EYE_LASER;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            Entity var7 = entityHitResult.getEntity();
            Entity entity = this.getOwner();
            DamageSource damageSource = this.getDamageSources().indirectMagic(this, entity);
            var7.damage(damageSource, 1.0F);
            EnchantmentHelper.onTargetDamaged(serverWorld, var7, damageSource);
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
                        5, 0, 0, 0, 0.01
                );
                this.discard();
            }
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {}
}