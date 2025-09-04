package net.bluethedude.terrarianmight.datagen;

import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.util.TerrarianTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class TerrarianBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public TerrarianBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(TerrarianBlocks.SMALL_LIFE_CRYSTAL_BUD)
                .add(TerrarianBlocks.MEDIUM_LIFE_CRYSTAL_BUD)
                .add(TerrarianBlocks.LARGE_LIFE_CRYSTAL_BUD)
                .add(TerrarianBlocks.LIFE_CRYSTAL_CLUSTER)
                .addTag(TerrarianTags.Blocks.BUDDING_LIFE_CRYSTALS)
                .add(TerrarianBlocks.LIFE_CRYSTAL_BLOCK)
                .addTag(TerrarianTags.Blocks.HEART_LANTERNS);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .add(TerrarianBlocks.YELLOW_WILLOW_LOG)
                .add(TerrarianBlocks.YELLOW_WILLOW_WOOD)
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG)
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_WOOD)
                .add(TerrarianBlocks.YELLOW_WILLOW_PLANKS);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .add(TerrarianBlocks.YELLOW_WILLOW_LOG)
                .add(TerrarianBlocks.YELLOW_WILLOW_WOOD)
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG)
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_WOOD);

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
                .add(TerrarianBlocks.YELLOW_WILLOW_LOG)
                .add(TerrarianBlocks.YELLOW_WILLOW_WOOD)
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG)
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_WOOD);

        getOrCreateTagBuilder(TerrarianTags.Blocks.YELLOW_WILLOW_LOGS)
                .add(TerrarianBlocks.YELLOW_WILLOW_LOG)
                .add(TerrarianBlocks.YELLOW_WILLOW_WOOD)
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG)
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_WOOD);

        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(TerrarianBlocks.YELLOW_WILLOW_PLANKS);

        getOrCreateTagBuilder(BlockTags.BUTTONS)
                .add(TerrarianBlocks.YELLOW_WILLOW_BUTTON);

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(TerrarianBlocks.YELLOW_WILLOW_BUTTON);

        getOrCreateTagBuilder(BlockTags.PRESSURE_PLATES)
                .add(TerrarianBlocks.YELLOW_WILLOW_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(TerrarianBlocks.YELLOW_WILLOW_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.FENCES)
                .add(TerrarianBlocks.YELLOW_WILLOW_FENCE);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(TerrarianBlocks.YELLOW_WILLOW_FENCE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(TerrarianBlocks.YELLOW_WILLOW_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.DOORS)
                .add(TerrarianBlocks.YELLOW_WILLOW_DOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(TerrarianBlocks.YELLOW_WILLOW_DOOR);

        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(TerrarianBlocks.YELLOW_WILLOW_SIGN);

        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(TerrarianBlocks.YELLOW_WILLOW_WALL_SIGN);

        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(TerrarianBlocks.YELLOW_WILLOW_HANGING_SIGN);

        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(TerrarianBlocks.YELLOW_WILLOW_WALL_HANGING_SIGN);

        getOrCreateTagBuilder(TerrarianTags.Blocks.BUDDING_LIFE_CRYSTALS)
                .add(TerrarianBlocks.BUDDING_LIFE_CRYSTAL)
                .add(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL);

        getOrCreateTagBuilder(ConventionalBlockTags.BUDDING_BLOCKS)
                .add(TerrarianBlocks.BUDDING_LIFE_CRYSTAL)
                .add(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL);

        getOrCreateTagBuilder(ConventionalBlockTags.ORES)
                .add(TerrarianBlocks.BUDDING_LIFE_CRYSTAL)
                .add(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL);

        getOrCreateTagBuilder(TerrarianTags.Blocks.HEART_LANTERNS)
                .add(TerrarianBlocks.HEART_LANTERN)
                .add(TerrarianBlocks.CHIPPED_HEART_LANTERN)
                .add(TerrarianBlocks.DAMAGED_HEART_LANTERN)
                .add(TerrarianBlocks.BROKEN_HEART_LANTERN);
    }
}
