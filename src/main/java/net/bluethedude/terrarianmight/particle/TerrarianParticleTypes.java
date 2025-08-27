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
    public static final SimpleParticleType ITEM_PORKCHOP =
            registerParticle("item_porkchop", FabricParticleTypes.simple());
    public static final SimpleParticleType MAGIC_BOLT =
            registerParticle("magic_bolt", FabricParticleTypes.simple());
    public static final SimpleParticleType MAGIC_BOLT_SMALL =
            registerParticle("magic_bolt_small", FabricParticleTypes.simple());
    public static final SimpleParticleType EYE_LASER =
            registerParticle("eye_laser", FabricParticleTypes.simple());

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(TerrarianMight.MOD_ID, name), particleType);
    }

    public static void registerParticles() {
        TerrarianMight.LOGGER.info("Registering Particles for " + TerrarianMight.MOD_ID);
    }
}
