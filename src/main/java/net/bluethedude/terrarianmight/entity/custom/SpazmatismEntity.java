package net.bluethedude.terrarianmight.entity.custom;

import net.bluethedude.terrarianmight.entity.custom.util.AbstractSummonEntity;
import net.bluethedude.terrarianmight.item.custom.OpticStaffItem;
import net.bluethedude.terrarianmight.sound.TerrarianSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.control.BodyControl;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;

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
        this.goalSelector.add(5, new SpazmatismAttackGoal(this, 2.0, true));
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
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 50.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0);
    }

    @Override
    protected void playAttackSound() {
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

    //Spazmatism custom attack goal goes here :)
    public static class SpazmatismAttackGoal extends Goal {
        protected final PathAwareEntity mob;
        private final double speed;
        private final boolean pauseWhenMobIdle;
        private Path path;
        private double targetX;
        private double targetY;
        private double targetZ;
        private int updateCountdownTicks;
        private int cooldown;
        private long lastUpdateTime;

        public SpazmatismAttackGoal(PathAwareEntity mob, double speed, boolean pauseWhenMobIdle) {
            this.mob = mob;
            this.speed = speed;
            this.pauseWhenMobIdle = pauseWhenMobIdle;
            this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
        }

        @Override
        public boolean canStart() {
            long l = this.mob.getWorld().getTime();
            if (l - this.lastUpdateTime < 20L) {
                return false;
            } else {
                this.lastUpdateTime = l;
                LivingEntity livingEntity = this.mob.getTarget();
                if (livingEntity == null) {
                    return false;
                } else if (!livingEntity.isAlive()) {
                    return false;
                } else {
                    this.path = this.mob.getNavigation().findPathTo(livingEntity, 0);
                    return this.path != null || this.mob.isInAttackRange(livingEntity);
                }
            }
        }

        @Override
        public boolean shouldContinue() {
            LivingEntity livingEntity = this.mob.getTarget();
            if (livingEntity == null) {
                return false;
            } else if (!livingEntity.isAlive()) {
                return false;
            } else if (!this.pauseWhenMobIdle) {
                return !this.mob.getNavigation().isIdle();
            } else {
                return this.mob.isInWalkTargetRange(livingEntity.getBlockPos()) &&
                        (!(livingEntity instanceof PlayerEntity) || !livingEntity.isSpectator() && !((PlayerEntity) livingEntity).isCreative());
            }
        }

        @Override
        public void start() {
            this.mob.getNavigation().startMovingAlong(this.path, this.speed);
            this.mob.setAttacking(true);
            this.updateCountdownTicks = 0;
            this.cooldown = 0;
        }

        @Override
        public void stop() {
            LivingEntity livingEntity = this.mob.getTarget();
            if (!EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(livingEntity)) {
                this.mob.setTarget(null);
            }

            this.mob.setAttacking(false);
            this.mob.getNavigation().stop();
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            LivingEntity livingEntity = this.mob.getTarget();
            if (livingEntity != null) {
                this.mob.getLookControl().lookAt(livingEntity, 30.0F, 30.0F);
                this.updateCountdownTicks = Math.max(this.updateCountdownTicks - 1, 0);
                if ((this.pauseWhenMobIdle || this.mob.getVisibilityCache().canSee(livingEntity))
                        && this.updateCountdownTicks <= 0
                        && (
                        this.targetX == 0.0 && this.targetY == 0.0 && this.targetZ == 0.0
                                || livingEntity.squaredDistanceTo(this.targetX, this.targetY, this.targetZ) >= 1.0
                                || this.mob.getRandom().nextFloat() < 0.05F
                )) {
                    this.targetX = livingEntity.getX();
                    this.targetY = livingEntity.getY();
                    this.targetZ = livingEntity.getZ();
                    this.updateCountdownTicks = 4 + this.mob.getRandom().nextInt(7);
                    double d = this.mob.squaredDistanceTo(livingEntity);
                    if (d > 1024.0) {
                        this.updateCountdownTicks += 10;
                    } else if (d > 256.0) {
                        this.updateCountdownTicks += 5;
                    }

                    if (!this.mob.getNavigation().startMovingTo(livingEntity, this.speed)) {
                        this.updateCountdownTicks += 15;
                    }

                    this.updateCountdownTicks = this.getTickCount(this.updateCountdownTicks);
                }

                this.cooldown = Math.max(this.cooldown - 1, 0);
                this.attack(livingEntity);
            }
        }

        protected void attack(LivingEntity target) {
            if (this.canAttack(target)) {
                this.resetCooldown();
                this.mob.swingHand(Hand.MAIN_HAND);
                this.mob.tryAttack(target);
                this.spawnBiteParticles();
            }
        }

        protected void spawnBiteParticles() {
            World world = this.mob.getWorld();
            ((ServerWorld) world).spawnParticles(ParticleTypes.CRIT,
                    this.mob.getX(),
                    this.mob.getEyeY(),
                    this.mob.getZ(),
                    8, 0.5, 0.1, 0.5, 0
            );
        }

        protected void resetCooldown() {
            this.cooldown = this.getTickCount(20);
        }

        protected boolean isCooledDown() {
            return this.cooldown <= 0;
        }

        protected boolean canAttack(LivingEntity target) {
            return this.isCooledDown() && this.mob.isInAttackRange(target) && this.mob.getVisibilityCache().canSee(target);
        }
    }
}
