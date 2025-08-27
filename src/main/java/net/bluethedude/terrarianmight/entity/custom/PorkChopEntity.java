package net.bluethedude.terrarianmight.entity.custom;

import net.bluethedude.terrarianmight.entity.TerrarianEntityTypes;
import net.bluethedude.terrarianmight.particle.TerrarianParticleTypes;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class PorkChopEntity extends ThrownItemEntity {
    public PorkChopEntity(EntityType<? extends PorkChopEntity> entityType, World world) {
        super(entityType, world);
    }

    public PorkChopEntity(World world, LivingEntity owner) {
        super(TerrarianEntityTypes.PORK_CHOP, owner, world);
    }

    public PorkChopEntity(World world, double x, double y, double z) {
        super(TerrarianEntityTypes.PORK_CHOP, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return Items.COOKED_PORKCHOP;
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getStack();
        return !itemStack.isEmpty() && !itemStack.isOf(this.getDefaultItem())
                ? new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack)
                : TerrarianParticleTypes.ITEM_PORKCHOP;
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for (int i = 0; i < 8; i++) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        if (this.getWorld() instanceof ServerWorld serverWorld) {
            Entity var7 = entityHitResult.getEntity();
            Entity entity2 = this.getOwner();
            DamageSource damageSource = this.getDamageSources().indirectMagic(this, entity2);
            var7.damage(damageSource, 0.5F);
            EnchantmentHelper.onTargetDamaged(serverWorld, var7, damageSource);
        }
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
        }
    }
}
