package net.bluethedude.terrarianmight.entity;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.entity.custom.SlimeWolfEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class TerrarianEntityTypes {

    private static final RegistryKey<EntityType<?>> SLIME_WOLF_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TerrarianMight.MOD_ID, "slime_wolf"));

    public static final EntityType<SlimeWolfEntity> SLIME_WOLF = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TerrarianMight.MOD_ID, "slime_wolf"),
            EntityType.Builder.create(SlimeWolfEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 2.5f).build(String.valueOf(SLIME_WOLF_KEY)));

    public static void registerTerrarianEntities() {
        TerrarianMight.LOGGER.info("Registering Mod Entities for " + TerrarianMight.MOD_ID);
    }
}
