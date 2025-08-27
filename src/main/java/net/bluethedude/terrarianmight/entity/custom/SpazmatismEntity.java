package net.bluethedude.terrarianmight.entity.custom;

import net.bluethedude.terrarianmight.entity.custom.util.AbstractSummonEntity;
import net.bluethedude.terrarianmight.item.custom.OpticStaffItem;
import net.bluethedude.terrarianmight.sound.TerrarianSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.control.BodyControl;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SpazmatismEntity extends AbstractSummonEntity {
    public SpazmatismEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.setTamed(false, false);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

    @Override
    protected BodyControl createBodyControl() {
        return new SpazmatismEntity.TwinBodyControl(this);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new TameableEscapeDangerGoal(1.5, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.add(5, new MeleeAttackGoal(this, 2.0, true));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0, 8.0F, 2.0F));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 1.0));
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
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2F)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.2F)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected void playAttackSound() {
        World world = this.getWorld();
        ((ServerWorld) world).spawnParticles(ParticleTypes.CRIT,
                this.getX(),
                this.getEyeY(),
                this.getZ(),
                8, 0.5, 0.1, 0.5, 0
        );
        this.playSound(TerrarianSoundEvents.ENTITY_TWINS_BITE, 0.6F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return TerrarianSoundEvents.ENTITY_TWINS_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return TerrarianSoundEvents.ENTITY_TWINS_DEATH;
    }

    @Override
    public int getMaxLifetime() {
        return OpticStaffItem.SUMMON_LIFESPAN;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {}

    @Override
    public void travel(Vec3d movementInput) {
        if (this.isLogicalSideForUpdatingMovement()) {
            if (this.isTouchingWater()) {
                this.updateVelocity(0.02F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.8F));
            } else if (this.isInLava()) {
                this.updateVelocity(0.02F, movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.5));
            } else {
                this.updateVelocity(this.getMovementSpeed(), movementInput);
                this.move(MovementType.SELF, this.getVelocity());
                this.setVelocity(this.getVelocity().multiply(0.91F));
            }
        }
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    class TwinBodyControl extends BodyControl {
        public TwinBodyControl(final MobEntity entity) {
            super(entity);
        }

        @Override
        public void tick() {
            SpazmatismEntity.this.headYaw = SpazmatismEntity.this.bodyYaw;
            SpazmatismEntity.this.bodyYaw = SpazmatismEntity.this.getYaw();
        }
    }
}
