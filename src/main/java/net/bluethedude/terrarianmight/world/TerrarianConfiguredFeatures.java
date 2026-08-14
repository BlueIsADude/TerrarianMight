package net.bluethedude.terrarianmight.world;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.CherryFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;

import java.util.List;

public class TerrarianConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> BUDDING_LIFE_CRYSTAL = registerKey("budding_life_crystal");

    public static final RegistryKey<ConfiguredFeature<?, ?>> YELLOW_WILLOW = registerKey("yellow_willow");

    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_MOSS_VEGETATION = registerKey("glowing_moss_vegetation");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_MOSS_PATCH = registerKey("glowing_moss_patch");
    public static final RegistryKey<ConfiguredFeature<?, ?>> GLOWING_MOSS_PATCH_BONEMEAL = registerKey("glowing_moss_patch_bonemeal");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_GLOWING_MUSHROOM = registerKey("glowing_mushroom");

    public static TreeFeatureConfig.Builder yellowWillow() {
        return new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TerrarianBlocks.YELLOW_WILLOW_LOG),
                new LargeOakTrunkPlacer(9, 7, 2),
                BlockStateProvider.of(TerrarianBlocks.YELLOW_WILLOW_LEAVES),
                new CherryFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), ConstantIntProvider.create(6),
                        0.5F, 0.7F, 0.3F, 0.6F),
                new TwoLayersFeatureSize(1, 0, 2)).ignoreVines();
    }

    public static void bootstrap(Registerable<ConfiguredFeature<?, ?>> context) {
        RegistryEntryLookup<ConfiguredFeature<?, ?>> registryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        List<OreFeatureConfig.Target> buddingLifeCrystals = getTargets();

        register(context, BUDDING_LIFE_CRYSTAL, Feature.ORE, new OreFeatureConfig(buddingLifeCrystals, 3));

        register(context, YELLOW_WILLOW, Feature.TREE, yellowWillow().build());

        ConfiguredFeatures.register(context, GLOWING_MOSS_VEGETATION,
                Feature.SIMPLE_BLOCK,
                new SimpleBlockFeatureConfig(
                        new WeightedBlockStateProvider(
                                DataPool.<BlockState>builder()
                                        .add(TerrarianBlocks.GLOWING_MUSHROOM.getDefaultState(), 7)
                                        .add(TerrarianBlocks.GLOWING_MOSS_CARPET.getDefaultState(), 25)
                        )
                )
        );
        ConfiguredFeatures.register(context, GLOWING_MOSS_PATCH,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.MOSS_REPLACEABLE,
                        BlockStateProvider.of(TerrarianBlocks.GLOWING_MOSS_BLOCK),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(GLOWING_MOSS_VEGETATION)),
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(1),
                        0.0F,
                        5,
                        0.8F,
                        UniformIntProvider.create(4, 7),
                        0.3F
                )
        );
        ConfiguredFeatures.register(context, GLOWING_MOSS_PATCH_BONEMEAL,
                Feature.VEGETATION_PATCH,
                new VegetationPatchFeatureConfig(
                        BlockTags.MOSS_REPLACEABLE,
                        BlockStateProvider.of(TerrarianBlocks.GLOWING_MOSS_BLOCK),
                        PlacedFeatures.createEntry(registryEntryLookup.getOrThrow(GLOWING_MOSS_VEGETATION)),
                        VerticalSurfaceType.FLOOR,
                        ConstantIntProvider.create(1),
                        0.0F,
                        5,
                        0.6F,
                        UniformIntProvider.create(1, 2),
                        0.75F
                )
        );

        ConfiguredFeatures.register(context, HUGE_GLOWING_MUSHROOM,
                Feature.HUGE_RED_MUSHROOM,
                new HugeMushroomFeatureConfig(
                        BlockStateProvider.of(Blocks.BLUE_WOOL.getDefaultState()),
                        BlockStateProvider.of(Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false)),
                        1
                )
        );
    }

    private static List<OreFeatureConfig.Target> getTargets() {
        RuleTest stoneReplaceables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        List<OreFeatureConfig.Target> buddingLifeCrystals =
                List.of(OreFeatureConfig.createTarget(stoneReplaceables, TerrarianBlocks.BUDDING_LIFE_CRYSTAL.getDefaultState()),
                        OreFeatureConfig.createTarget(deepslateReplaceables, TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL.getDefaultState()));
        return buddingLifeCrystals;
    }

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(TerrarianMight.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
