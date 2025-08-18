package net.bluethedude.terrarianmight.particle;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TerrarianParticleTypes {
    public static final SimpleParticleType LIFE_HEART =
            registerParticle("life_heart", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(TerrarianMight.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        TerrarianMight.LOGGER.info("Registering Particles for " + TerrarianMight.MOD_ID);
    }
}
