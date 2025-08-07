package net.bluethedude.terrarianmight.world;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class TerrarianConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> BUDDING_LIFE_CRYSTAL_KEY = registerKey("budding_life_crystal");

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> buddingLifeCrystals =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, TerrarianBlocks.BUDDING_LIFE_CRYSTAL.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL.getDefaultState()));

        register(context, BUDDING_LIFE_CRYSTAL_KEY, Feature.ORE, new OreFeatureConfig(buddingLifeCrystals, 3));
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(TerrarianMight.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
