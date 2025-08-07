package net.bluethedude.terrarianmight.world.gen;

import net.bluethedude.terrarianmight.world.TerrarianPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class TerrarianOreGeneration {
    public static void generateOres() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                TerrarianPlacedFeatures.BUDDING_LIFE_CRYSTAL_PLACED_KEY);
    }
}
