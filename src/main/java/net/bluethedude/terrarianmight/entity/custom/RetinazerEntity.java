package net.bluethedude.terrarianmight.entity.custom;

import net.bluethedude.terrarianmight.entity.custom.util.AbstractSummonEntity;
import net.bluethedude.terrarianmight.sound.TerrarianSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.RangedAttackMob;
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
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class RetinazerEntity extends AbstractSummonEntity implements RangedAttackMob {
    public RetinazerEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.setTamed(false, false);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

    @Override
    protected BodyControl createBodyControl() {
        return new RetinazerEntity.TwinBodyControl(this);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new TameableEscapeDangerGoal(1.5, DamageTypeTags.PANIC_ENVIRONMENTAL_CAUSES));
        this.goalSelector.add(5, new RetinazerAttackGoal(this, 1.5, 12, 8.0f));
        this.goalSelector.add(6, new FollowOwnerGoal(this, 1.0, 6.0F, 2.0F));
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
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0);
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
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

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

    @Override
    public void shootAt(LivingEntity target, float pullProgress) {
        EndLaserEntity endLaserEntity = new EndLaserEntity(this.getWorld(), this);
        double d = target.getX() - this.getX();
        double e = target.getEyeY() - this.getY();
        double f = target.getZ() - this.getZ();
        double g = e - 1.1F;
        double h = Math.sqrt(d * d + f * f) * 0.2F;
        endLaserEntity.setVelocity(d, g + h, f, 1.5F, 1.0F);
        endLaserEntity.maxLifespan = 16;
        this.playSound(TerrarianSoundEvents.ENTITY_TWINS_LASER_FIRE, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.getWorld().spawnEntity(endLaserEntity);
    }

    class TwinBodyControl extends BodyControl {
        public TwinBodyControl(final MobEntity entity) {
            super(entity);
        }

        @Override
        public void tick() {
            RetinazerEntity.this.headYaw = RetinazerEntity.this.bodyYaw;
            RetinazerEntity.this.bodyYaw = RetinazerEntity.this.getYaw();
        }
    }

    // Retinazer custom attack goal goes here :)
    public static class RetinazerAttackGoal extends Goal {
        private final RetinazerEntity mob;
        private final RangedAttackMob owner;
        @Nullable
        private LivingEntity target;
        private int updateCountdownTicks = -1;
        private final double mobSpeed;
        private int seenTargetTicks;
        private final int minIntervalTicks;
        private final int maxIntervalTicks;
        private final float maxShootRange;
        private final float squaredMaxShootRange;
        private boolean movingToLeft;
        private boolean backward;
        private int combatTicks = -1;

        public RetinazerAttackGoal(RangedAttackMob mob, double mobSpeed, int intervalTicks, float maxShootRange) {
            this(mob, mobSpeed, intervalTicks, intervalTicks, maxShootRange);
        }

        public RetinazerAttackGoal(RangedAttackMob mob, double mobSpeed, int minIntervalTicks, int maxIntervalTicks, float maxShootRange) {
            if (!(mob instanceof LivingEntity)) {
                throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
            } else {
                this.owner = mob;
                this.mob = (RetinazerEntity) mob;
                this.mobSpeed = mobSpeed;
                this.minIntervalTicks = minIntervalTicks;
                this.maxIntervalTicks = maxIntervalTicks;
                this.maxShootRange = maxShootRange;
                this.squaredMaxShootRange = maxShootRange * maxShootRange;
                this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
            }
        }

        @Override
        public boolean canStart() {
            LivingEntity livingEntity = this.mob.getTarget();
            LivingEntity owner = this.mob.getOwner();
            if (livingEntity == null) {
                return false;
            } else if (!livingEntity.isAlive()) {
                return false;
            } else if (!this.mob.canAttackWithOwner(livingEntity, owner)) {
                return false;
            } else if (this.mob.getAngerTime() == 0) {
                return false;
            } else {
                this.target = livingEntity;
                return true;
            }
        }

        @Override
        public boolean shouldContinue() {
            if (this.canStart()) return true;
            assert this.target != null;
            return this.target.isAlive() && !this.mob.getNavigation().isIdle();
        }

        @Override
        public void stop() {
            this.target = null;
            this.seenTargetTicks = 0;
            this.updateCountdownTicks = -1;
        }

        @Override
        public boolean shouldRunEveryTick() {
            return true;
        }

        @Override
        public void tick() {
            assert this.target != null;
            double d = this.mob.squaredDistanceTo(this.target.getX(), this.target.getY(), this.target.getZ());
            boolean bl = this.mob.getVisibilityCache().canSee(this.target);
            if (bl) {
                this.seenTargetTicks++;
            } else {
                this.seenTargetTicks = 0;
            }

            if (!(d > this.squaredMaxShootRange) && this.seenTargetTicks >= 20) {
                this.mob.getNavigation().stop();
                this.combatTicks++;
            } else {
                this.mob.getNavigation().startMovingTo(this.target, this.mobSpeed);
                this.combatTicks = -1;
            }

            if (this.combatTicks >= 20) {
                if (this.mob.getRandom().nextFloat() < 0.3) {
                    this.movingToLeft = !this.movingToLeft;
                }

                if (this.mob.getRandom().nextFloat() < 0.3) {
                    this.backward = !this.backward;
                }

                this.combatTicks = 0;
            }

            if (this.combatTicks > -1) {
                if (d > this.squaredMaxShootRange * 0.75F) {
                    this.backward = false;
                } else if (d < this.squaredMaxShootRange * 0.25F) {
                    this.backward = true;
                }
                this.mob.getMoveControl().strafeTo(this.backward ? -0.5F : 0.5F, this.movingToLeft ? 0.5F : -0.5F);
            }
            this.mob.lookAtEntity(target, 180.0F, 180.0F);
            this.mob.getLookControl().lookAt(target);
            if (--this.updateCountdownTicks == 0) {
                if (!bl) {
                    return;
                }

                float f = (float)Math.sqrt(d) / this.maxShootRange;
                float g = MathHelper.clamp(f, 0.1F, 1.0F);
                this.owner.shootAt(this.target, g);
                this.updateCountdownTicks = MathHelper.floor(f * (this.maxIntervalTicks - this.minIntervalTicks) + this.minIntervalTicks);
            } else if (this.updateCountdownTicks < 0) {
                this.updateCountdownTicks = MathHelper.floor(
                        MathHelper.lerp(Math.sqrt(d) / this.maxShootRange, this.minIntervalTicks, this.maxIntervalTicks)
                );
            }
        }
    }
}
