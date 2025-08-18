package net.bluethedude.terrarianmight.world.gen;

import net.bluethedude.terrarianmight.world.TerrarianPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class TerrarianTreeGeneration {
    public static void generateTrees() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(
                        BiomeKeys.PLAINS,
                        BiomeKeys.SUNFLOWER_PLAINS,
                        BiomeKeys.MEADOW,
                        BiomeKeys.FOREST,
                        BiomeKeys.BIRCH_FOREST,
                        BiomeKeys.OLD_GROWTH_BIRCH_FOREST,
                        BiomeKeys.FLOWER_FOREST
                ),
                GenerationStep.Feature.VEGETAL_DECORATION, TerrarianPlacedFeatures.YELLOW_WILLOW_PLACED
        );
    }
}
