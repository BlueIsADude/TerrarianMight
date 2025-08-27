package net.bluethedude.terrarianmight.entity;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.entity.custom.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class TerrarianEntityTypes {

    private static final RegistryKey<EntityType<?>> MAGIC_BOLT_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TerrarianMight.MOD_ID, "magic_bolt"));
    private static final RegistryKey<EntityType<?>> PORK_CHOP_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TerrarianMight.MOD_ID, "pork_chop"));
    private static final RegistryKey<EntityType<?>> END_LASER_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TerrarianMight.MOD_ID, "end_laser"));


    private static final RegistryKey<EntityType<?>> SLIME_WOLF_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TerrarianMight.MOD_ID, "slime_wolf"));

    private static final RegistryKey<EntityType<?>> SPAZ_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TerrarianMight.MOD_ID, "spazmatism"));
    private static final RegistryKey<EntityType<?>> REZ_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(TerrarianMight.MOD_ID, "retinazer"));

    public static final EntityType<MagicBoltEntity> MAGIC_BOLT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TerrarianMight.MOD_ID, "magic_bolt"),
            EntityType.Builder.<MagicBoltEntity>create(MagicBoltEntity::new, SpawnGroup.MISC)
                    .dimensions(0.35f, 0.35f)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
                    .build(String.valueOf(MAGIC_BOLT_KEY)));
    public static final EntityType<PorkChopEntity> PORK_CHOP = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TerrarianMight.MOD_ID, "pork_chop"),
            EntityType.Builder.<PorkChopEntity>create(PorkChopEntity::new, SpawnGroup.MISC)
                    .dimensions(0.25f, 0.25f)
                    .maxTrackingRange(4)
                    .trackingTickInterval(10)
                    .build(String.valueOf(PORK_CHOP_KEY)));
    public static final EntityType<EndLaserEntity> END_LASER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TerrarianMight.MOD_ID, "end_laser"),
            EntityType.Builder.<EndLaserEntity>create(EndLaserEntity::new, SpawnGroup.MISC)
                    .dimensions(0.3125f, 0.3125f)
                    .eyeHeight(0.13F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(20)
                    .build(String.valueOf(END_LASER_KEY)));

    public static final EntityType<SlimeWolfEntity> SLIME_WOLF = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TerrarianMight.MOD_ID, "slime_wolf"),
            EntityType.Builder.create(SlimeWolfEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.6F, 0.85F)
                    .eyeHeight(0.68F)
                    .passengerAttachments(new Vec3d(0.0, 0.81875, -0.0625))
                    .maxTrackingRange(10)
                    .build(String.valueOf(SLIME_WOLF_KEY)));

    public static final EntityType<SpazmatismEntity> SPAZ = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TerrarianMight.MOD_ID, "spazmatism"),
            EntityType.Builder.create(SpazmatismEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.6f, 0.5f)
                    .eyeHeight(0.25F)
                    .maxTrackingRange(8)
                    .trackingTickInterval(2)
                    .build(String.valueOf(SPAZ_KEY)));
    public static final EntityType<RetinazerEntity> REZ = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(TerrarianMight.MOD_ID, "retinazer"),
            EntityType.Builder.create(RetinazerEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.6f, 0.5f)
                    .eyeHeight(0.25F)
                    .maxTrackingRange(8)
                    .trackingTickInterval(2)
                    .build(String.valueOf(REZ_KEY)));

    public static void registerTerrarianEntities() {
        TerrarianMight.LOGGER.info("Registering Mod Entities for " + TerrarianMight.MOD_ID);
    }
}
