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
                .add(TerrarianBlocks.BUDDING_LIFE_CRYSTAL)
                .add(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL)
                .add(TerrarianBlocks.LIFE_CRYSTAL_BLOCK);

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
