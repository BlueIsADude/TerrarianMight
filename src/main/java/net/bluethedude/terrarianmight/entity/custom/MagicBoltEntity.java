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

public class MagicBoltEntity extends AbstractMagicProjectileEntity {

    public MagicBoltEntity(EntityType<? extends MagicBoltEntity> entityType, World world) {
        super(entityType, world);
    }

    public MagicBoltEntity(World world, LivingEntity owner) {
        super(TerrarianEntityTypes.MAGIC_BOLT, owner, world);
    }

    public MagicBoltEntity(World world, double x, double y, double z) {
        super(TerrarianEntityTypes.MAGIC_BOLT, x, y, z, world);
    }

    @Override
    @Nullable
    protected ParticleEffect getFlyingParticleType() {
        return TerrarianParticleTypes.MAGIC_BOLT_SMALL;
    }

    @Override
    @Nullable
    protected ParticleEffect getCrashingParticleType() {
        return TerrarianParticleTypes.MAGIC_BOLT;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            Entity entity = entityHitResult.getEntity();
            Entity owner = this.getOwner();
            DamageSource damageSource = this.getDamageSources().indirectMagic(this, owner);
            entity.damage(damageSource, 4.0F);
            EnchantmentHelper.onTargetDamaged(serverWorld, entity, damageSource);
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {}
}