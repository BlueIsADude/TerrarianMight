package net.bluethedude.terrarianmight.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class LifeHeartParticle extends SpriteBillboardParticle {

    LifeHeartParticle(ClientWorld clientWorld, double x, double y, double z) {
        super(clientWorld, x, y, z, 0.0, 0.0, 0.0);
        this.ascending = true;
        this.velocityMultiplier = 0.86F;
        this.velocityX *= 0.01F;
        this.velocityY *= 0.01F;
        this.velocityZ *= 0.01F;
        this.velocityY += 0.1;
        this.scale *= 1.0F;
        this.maxAge = 16;
        this.collidesWithWorld = false;
    }

    @Override
    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(this.getBoundingBox().offset(dx, dy, dz));
        this.repositionFromBoundingBox();
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public float getSize(float tickDelta) {
        return this.scale * MathHelper.clamp((this.age + tickDelta) / this.maxAge * 32.0F, 0.0F, 1.0F);
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            LifeHeartParticle lifeHeartParticle = new LifeHeartParticle(clientWorld, d, e, f);
            lifeHeartParticle.setSprite(this.spriteProvider);
            return lifeHeartParticle;
        }
    }
}
