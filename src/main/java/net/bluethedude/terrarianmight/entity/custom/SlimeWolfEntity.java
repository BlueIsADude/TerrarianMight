package net.bluethedude.terrarianmight.entity.custom;

import net.bluethedude.terrarianmight.entity.custom.util.AbstractSummonEntity;
import net.bluethedude.terrarianmight.item.custom.SlimeStaffItem;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class SlimeWolfEntity extends AbstractSummonEntity {
    public SlimeWolfEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.setTamed(false, false);
        this.setPathfindingPenalty(PathNodeType.POWDER_SNOW, -1.0F);
        this.setPathfindingPenalty(PathNodeType.DANGER_POWDER_SNOW, -1.0F);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new TameableEntity.TameableEscapeDangerGoal(1.5, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.add(4, new PounceAtTargetGoal(this, 0.4F));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 1.0, true));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0, 10.0F, 2.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(10, new LookAroundGoal(this));

        this.targetSelector.add(1, new TrackOwnerAttackerGoal(this));
        this.targetSelector.add(2, new AttackWithOwnerGoal(this));
        this.targetSelector.add(3, new RevengeGoal(this).setGroupRevenge());
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        this.targetSelector.add(8, new UniversalAngerGoal<>(this, true));
    }

    public static DefaultAttributeContainer.Builder createSummonAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4F)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0);
    }

    @Override
    public int getMaxLifetime() {
        return SlimeStaffItem.SUMMON_LIFESPAN;
    }

    protected ParticleEffect getParticles() {
        return ParticleTypes.ITEM_SLIME;
    }

    @Override
    public void onDamaged(DamageSource damageSource) {
        float f = this.getDimensions(this.getPose()).width() * 2.0F;
        float g = f / 2.0F;

        for (int i = 0; i < f * 16.0F; i++) {
            float h = this.random.nextFloat() * (float) (Math.PI * 2);
            float j = this.random.nextFloat() * 0.5F + 0.5F;
            float k = MathHelper.sin(h) * g * j;
            float l = MathHelper.cos(h) * g * j;
            this.getWorld().addParticle(this.getParticles(), this.getX() + k, this.getEyeY(), this.getZ() + l, 0.0, 0.0, 0.0);
        }
        super.onDamaged(damageSource);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SLIME_HURT_SMALL;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SLIME_DEATH_SMALL;
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    public float getTailAngle() {
        if (this.hasAngerTime()) {
            return 1.5393804F;
        } else if (this.isTamed()) {
            float f = this.getMaxHealth();
            float g = (f - this.getHealth()) / f;
            return (0.55F - g * 0.4F) * (float) Math.PI;
        } else {
            return (float) (Math.PI / 5);
        }
    }
}
