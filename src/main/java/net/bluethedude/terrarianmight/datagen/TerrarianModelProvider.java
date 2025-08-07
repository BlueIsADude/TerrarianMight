package net.bluethedude.terrarianmight.datagen;

import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class TerrarianModelProvider extends FabricModelProvider {
    public TerrarianModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(TerrarianBlocks.BUDDING_LIFE_CRYSTAL);
        blockStateModelGenerator.registerSimpleCubeAll(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL);
        blockStateModelGenerator.registerSimpleCubeAll(TerrarianBlocks.LIFE_CRYSTAL_BLOCK);

        blockStateModelGenerator.registerAmethyst(TerrarianBlocks.SMALL_LIFE_CRYSTAL_BUD);
        blockStateModelGenerator.registerAmethyst(TerrarianBlocks.MEDIUM_LIFE_CRYSTAL_BUD);
        blockStateModelGenerator.registerAmethyst(TerrarianBlocks.LARGE_LIFE_CRYSTAL_BUD);
        blockStateModelGenerator.registerAmethyst(TerrarianBlocks.LIFE_CRYSTAL_CLUSTER);

        blockStateModelGenerator.registerLantern(TerrarianBlocks.HEART_LANTERN);
        blockStateModelGenerator.registerLantern(TerrarianBlocks.CHIPPED_HEART_LANTERN);
        blockStateModelGenerator.registerLantern(TerrarianBlocks.DAMAGED_HEART_LANTERN);
        blockStateModelGenerator.registerLantern(TerrarianBlocks.BROKEN_HEART_LANTERN);

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(TerrarianItems.LIFE_SHARD, Models.GENERATED);
        itemModelGenerator.register(TerrarianItems.LIFE_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(TerrarianItems.LIFE_FRUIT, Models.GENERATED);

        itemModelGenerator.register(TerrarianItems.MUSIC_DISC_HEARTFELT, Models.GENERATED);
    }
}
