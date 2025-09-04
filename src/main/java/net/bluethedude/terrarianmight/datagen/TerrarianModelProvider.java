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

        blockStateModelGenerator.registerFlowerPotPlant(
                TerrarianBlocks.YELLOW_WILLOW_SAPLING,
                TerrarianBlocks.POTTED_YELLOW_WILLOW_SAPLING,
                BlockStateModelGenerator.TintType.NOT_TINTED
        );
        blockStateModelGenerator.registerSimpleCubeAll(TerrarianBlocks.YELLOW_WILLOW_LEAVES);
        blockStateModelGenerator.registerLog(TerrarianBlocks.YELLOW_WILLOW_LOG).log(TerrarianBlocks.YELLOW_WILLOW_LOG).wood(TerrarianBlocks.YELLOW_WILLOW_WOOD);
        blockStateModelGenerator.registerLog(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG).log(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG).wood(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_WOOD);

        BlockStateModelGenerator.BlockTexturePool yellowWillowPool = blockStateModelGenerator.registerCubeAllModelTexturePool(TerrarianBlocks.YELLOW_WILLOW_PLANKS);
        yellowWillowPool.stairs(TerrarianBlocks.YELLOW_WILLOW_STAIRS);
        yellowWillowPool.slab(TerrarianBlocks.YELLOW_WILLOW_SLAB);
        yellowWillowPool.button(TerrarianBlocks.YELLOW_WILLOW_BUTTON);
        yellowWillowPool.pressurePlate(TerrarianBlocks.YELLOW_WILLOW_PRESSURE_PLATE);
        yellowWillowPool.fence(TerrarianBlocks.YELLOW_WILLOW_FENCE);
        yellowWillowPool.fenceGate(TerrarianBlocks.YELLOW_WILLOW_FENCE_GATE);

        blockStateModelGenerator.registerDoor(TerrarianBlocks.YELLOW_WILLOW_DOOR);
        blockStateModelGenerator.registerOrientableTrapdoor(TerrarianBlocks.YELLOW_WILLOW_TRAPDOOR);

        blockStateModelGenerator.registerHangingSign(
                TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG,
                TerrarianBlocks.YELLOW_WILLOW_HANGING_SIGN,
                TerrarianBlocks.YELLOW_WILLOW_WALL_HANGING_SIGN
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(TerrarianItems.YELLOW_WILLOW_SIGN_ITEM, Models.GENERATED);

        itemModelGenerator.register(TerrarianItems.YELLOW_WILLOW_BOAT, Models.GENERATED);
        itemModelGenerator.register(TerrarianItems.YELLOW_WILLOW_CHEST_BOAT, Models.GENERATED);

        itemModelGenerator.register(TerrarianItems.LIFE_SHARD, Models.GENERATED);
        itemModelGenerator.register(TerrarianItems.LIFE_CRYSTAL, Models.GENERATED);
        itemModelGenerator.register(TerrarianItems.LIFE_FRUIT, Models.GENERATED);

        itemModelGenerator.register(TerrarianItems.WAND_OF_SPARKING, Models.HANDHELD);
        itemModelGenerator.register(TerrarianItems.AMETHYST_STAFF, Models.HANDHELD);
        itemModelGenerator.register(TerrarianItems.ZEPHYR_SCEPTER, Models.HANDHELD);
        itemModelGenerator.register(TerrarianItems.SLIME_STAFF, Models.HANDHELD);
        itemModelGenerator.register(TerrarianItems.OPTIC_STAFF, Models.HANDHELD);

//        itemModelGenerator.register(TerrarianItems.MUSIC_DISC_HEARTFELT, Models.GENERATED);
    }
}
