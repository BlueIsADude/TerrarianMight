package net.bluethedude.terrarianmight.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.SimpleParticleType;

@Environment(EnvType.CLIENT)
public class TerrarianCrackParticle extends SpriteBillboardParticle {
    private final float sampleU;
    private final float sampleV;

    TerrarianCrackParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, ItemStack stack) {
        this(world, x, y, z, stack);
        this.velocityX *= 0.1F;
        this.velocityY *= 0.1F;
        this.velocityZ *= 0.1F;
        this.velocityX += velocityX;
        this.velocityY += velocityY;
        this.velocityZ += velocityZ;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.TERRAIN_SHEET;
    }

    protected TerrarianCrackParticle(ClientWorld world, double x, double y, double z, ItemStack stack) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.setSprite(MinecraftClient.getInstance().getItemRenderer().getModel(stack, world, null, 0).getParticleSprite());
        this.gravityStrength = 1.0F;
        this.scale /= 2.0F;
        this.sampleU = this.random.nextFloat() * 3.0F;
        this.sampleV = this.random.nextFloat() * 3.0F;
    }

    @Override
    protected float getMinU() {
        return this.sprite.getFrameU((this.sampleU + 1.0F) / 4.0F);
    }

    @Override
    protected float getMaxU() {
        return this.sprite.getFrameU(this.sampleU / 4.0F);
    }

    @Override
    protected float getMinV() {
        return this.sprite.getFrameV(this.sampleV / 4.0F);
    }

    @Override
    protected float getMaxV() {
        return this.sprite.getFrameV((this.sampleV + 1.0F) / 4.0F);
    }

    @Environment(EnvType.CLIENT)
    public static class PorkChopFactory implements ParticleFactory<SimpleParticleType> {
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new TerrarianCrackParticle(clientWorld, d, e, f, new ItemStack(Items.COOKED_PORKCHOP));
        }
    }
}
