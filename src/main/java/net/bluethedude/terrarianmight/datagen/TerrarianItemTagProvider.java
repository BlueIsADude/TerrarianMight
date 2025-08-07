package net.bluethedude.terrarianmight.datagen;

import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.bluethedude.terrarianmight.util.TerrarianTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class TerrarianItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public TerrarianItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ConventionalItemTags.MUSIC_DISCS)
                .add(TerrarianItems.MUSIC_DISC_HEARTFELT);

        getOrCreateTagBuilder(TerrarianTags.Items.BUDDING_LIFE_CRYSTALS)
                .add(TerrarianBlocks.BUDDING_LIFE_CRYSTAL.asItem())
                .add(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL.asItem());

        getOrCreateTagBuilder(ConventionalItemTags.BUDDING_BLOCKS)
                .add(TerrarianBlocks.BUDDING_LIFE_CRYSTAL.asItem())
                .add(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL.asItem());

        getOrCreateTagBuilder(ConventionalItemTags.ORES)
                .add(TerrarianBlocks.BUDDING_LIFE_CRYSTAL.asItem())
                .add(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL.asItem());

        getOrCreateTagBuilder(TerrarianTags.Items.HEART_LANTERNS)
                .add(TerrarianBlocks.HEART_LANTERN.asItem())
                .add(TerrarianBlocks.CHIPPED_HEART_LANTERN.asItem())
                .add(TerrarianBlocks.DAMAGED_HEART_LANTERN.asItem())
                .add(TerrarianBlocks.BROKEN_HEART_LANTERN.asItem());
    }
}

