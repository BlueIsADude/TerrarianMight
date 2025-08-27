package net.bluethedude.terrarianmight.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

@Environment(EnvType.CLIENT)
public class MagicBoltParticle extends AbstractSlowingParticle {
    MagicBoltParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f, g, h, i);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(this.getBoundingBox().offset(dx, dy, dz));
        this.repositionFromBoundingBox();
    }

    @Override
    public float getSize(float tickDelta) {
        float f = (this.age + tickDelta) / this.maxAge;
        return this.scale * (1.0F - f * f * 0.5F);
    }

    @Override
    public int getBrightness(float tint) {
        return 240;
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            MagicBoltParticle magicBoltParticle = new MagicBoltParticle(clientWorld, d, e, f, g, h, i);
            magicBoltParticle.setSprite(this.spriteProvider);
            return magicBoltParticle;
        }
    }

    @Environment(EnvType.CLIENT)
    public static class SmallFactory implements ParticleFactory<SimpleParticleType> {
        private final SpriteProvider spriteProvider;

        public SmallFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            MagicBoltParticle magicBoltParticle = new MagicBoltParticle(clientWorld, d, e, f, g, h, i);
            magicBoltParticle.setSprite(this.spriteProvider);
            magicBoltParticle.scale(0.5F);
            return magicBoltParticle;
        }
    }
}
