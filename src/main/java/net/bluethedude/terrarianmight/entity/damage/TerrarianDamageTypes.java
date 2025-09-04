package net.bluethedude.terrarianmight.entity.damage;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class TerrarianDamageTypes {
    public static final RegistryKey<DamageType> LASER = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(TerrarianMight.MOD_ID, "laser"));
    public static final RegistryKey<DamageType> SPARK_FIRE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(TerrarianMight.MOD_ID, "spark_fire"));

    public static void bootstrap(Registerable<DamageType> damageTypeRegisterable) {
        damageTypeRegisterable.register(LASER, new DamageType("terrarianmight.laser", 0.1F));
        damageTypeRegisterable.register(SPARK_FIRE, new DamageType("terrarianmight.spark_fire", 0.1F, DamageEffects.BURNING));
    }

    public static void registerTerrarianDamageTypes() {
        TerrarianMight.LOGGER.info("Registering Mod Damage Types for " + TerrarianMight.MOD_ID);
    }
}
