package net.bluethedude.terrarianmight.block;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.block.custom.BuddingLifeCrystalBlock;
import net.bluethedude.terrarianmight.block.custom.HeartLanternBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class TerrarianBlocks {

    public static final Block BUDDING_LIFE_CRYSTAL = registerBlock("budding_life_crystal",
            new BuddingLifeCrystalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_MAGENTA)
                    .ticksRandomly()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(3.0F, 3.0F)
                    .sounds(BlockSoundGroup.STONE)
                    .pistonBehavior(PistonBehavior.IGNORE)
            )
    );
    public static final Block DEEPSLATE_BUDDING_LIFE_CRYSTAL = registerBlock("deepslate_budding_life_crystal",
            new BuddingLifeCrystalBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_MAGENTA)
                    .ticksRandomly()
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresTool()
                    .strength(4.5F, 3.0F)
                    .sounds(BlockSoundGroup.DEEPSLATE)
                    .pistonBehavior(PistonBehavior.IGNORE)
            )
    );
    public static final Block LIFE_CRYSTAL_BLOCK = registerBlock("life_crystal_block",
            new AmethystBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_MAGENTA)
                    .requiresTool()
                    .strength(5.0F, 6.0F)
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK))
    );
    public static final Block LIFE_CRYSTAL_CLUSTER = registerBlock("life_crystal_cluster",
            new AmethystClusterBlock(
                    7.0F,
                    3.0F,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.TERRACOTTA_MAGENTA)
                            .solid()
                            .nonOpaque()
                            .requiresTool()
                            .strength(1.5F)
                            .luminance(state -> 5)
                            .sounds(BlockSoundGroup.AMETHYST_CLUSTER)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block SMALL_LIFE_CRYSTAL_BUD = registerBlock("small_life_crystal_bud",
            new AmethystClusterBlock(
                    3.0F,
                    4.0F,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.TERRACOTTA_MAGENTA)
                            .solid()
                            .nonOpaque()
                            .requiresTool()
                            .strength(1.5F)
                            .luminance(state -> 1)
                            .sounds(BlockSoundGroup.SMALL_AMETHYST_BUD)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block MEDIUM_LIFE_CRYSTAL_BUD = registerBlock("medium_life_crystal_bud",
            new AmethystClusterBlock(
                    4.0F,
                    3.0F,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.TERRACOTTA_MAGENTA)
                            .solid()
                            .nonOpaque()
                            .requiresTool()
                            .strength(1.5F)
                            .luminance(state -> 2)
                            .sounds(BlockSoundGroup.MEDIUM_AMETHYST_BUD)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block LARGE_LIFE_CRYSTAL_BUD = registerBlock("large_life_crystal_bud",
            new AmethystClusterBlock(
                    5.0F,
                    3.0F,
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.TERRACOTTA_MAGENTA)
                            .solid()
                            .nonOpaque()
                            .requiresTool()
                            .strength(1.5F)
                            .luminance(state -> 4)
                            .sounds(BlockSoundGroup.LARGE_AMETHYST_BUD)
                            .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block HEART_LANTERN = registerBlock("heart_lantern",
            new HeartLanternBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.IRON_GRAY)
                    .solid()
                    .requiresTool()
                    .strength(3.5F)
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance(state -> 15)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block CHIPPED_HEART_LANTERN = registerBlock("chipped_heart_lantern",
            new HeartLanternBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.IRON_GRAY)
                    .solid()
                    .requiresTool()
                    .strength(3.5F)
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance(state -> 10)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block DAMAGED_HEART_LANTERN = registerBlock("damaged_heart_lantern",
            new HeartLanternBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.IRON_GRAY)
                    .solid()
                    .requiresTool()
                    .strength(3.5F)
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance(state -> 5)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block BROKEN_HEART_LANTERN = registerBlock("broken_heart_lantern",
            new HeartLanternBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.IRON_GRAY)
                    .solid()
                    .requiresTool()
                    .strength(3.5F)
                    .sounds(BlockSoundGroup.LANTERN)
                    .luminance(state -> 0)
                    .nonOpaque()
                    .pistonBehavior(PistonBehavior.DESTROY)
            )
    );



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(TerrarianMight.MOD_ID, name), block);
    }

    private static Block registerItemlessBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(TerrarianMight.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TerrarianMight.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerTerrarianBlocks() {
        TerrarianMight.LOGGER.info("Registering Mod Blocks for " + TerrarianMight.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Blocks.AMETHYST_CLUSTER, BUDDING_LIFE_CRYSTAL);
            entries.addAfter(BUDDING_LIFE_CRYSTAL, DEEPSLATE_BUDDING_LIFE_CRYSTAL);
            entries.addAfter(DEEPSLATE_BUDDING_LIFE_CRYSTAL, SMALL_LIFE_CRYSTAL_BUD);
            entries.addAfter(SMALL_LIFE_CRYSTAL_BUD, MEDIUM_LIFE_CRYSTAL_BUD);
            entries.addAfter(MEDIUM_LIFE_CRYSTAL_BUD, LARGE_LIFE_CRYSTAL_BUD);
            entries.addAfter(LARGE_LIFE_CRYSTAL_BUD, LIFE_CRYSTAL_CLUSTER);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries ->
                entries.addAfter(Blocks.AMETHYST_BLOCK, LIFE_CRYSTAL_BLOCK));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Blocks.SOUL_LANTERN, HEART_LANTERN);
            entries.addAfter(HEART_LANTERN, CHIPPED_HEART_LANTERN);
            entries.addAfter(CHIPPED_HEART_LANTERN, DAMAGED_HEART_LANTERN);
            entries.addAfter(DAMAGED_HEART_LANTERN, BROKEN_HEART_LANTERN);
        });
    }
}
