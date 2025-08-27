package net.bluethedude.terrarianmight.block;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.block.custom.*;
import net.bluethedude.terrarianmight.world.tree.TerrarianSaplingGenerator;
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

    public static final Block YELLOW_WILLOW_LEAVES = registerBlock("yellow_willow_leaves",
            new LeavesBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .strength(0.2F)
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.CHERRY_LEAVES)
                    .nonOpaque()
                    .allowsSpawning(Blocks::canSpawnOnLeaves)
                    .suffocates(Blocks::never)
                    .blockVision(Blocks::never)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .solidBlock(Blocks::never)
            )
    );

    public static final Block YELLOW_WILLOW_LOG = registerBlock("yellow_willow_log",
            Blocks.createLogBlock(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_PINK, BlockSoundGroup.CHERRY_WOOD)
    );
    public static final Block YELLOW_WILLOW_WOOD = registerBlock("yellow_willow_wood",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_PINK)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sounds(BlockSoundGroup.CHERRY_WOOD)
                    .burnable()
            )
    );
    public static final Block STRIPPED_YELLOW_WILLOW_LOG = registerBlock("stripped_yellow_willow_log",
            Blocks.createLogBlock(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW, BlockSoundGroup.CHERRY_WOOD)
    );
    public static final Block STRIPPED_YELLOW_WILLOW_WOOD = registerBlock("stripped_yellow_willow_wood",
            new PillarBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F)
                    .sounds(BlockSoundGroup.CHERRY_WOOD)
                    .burnable()
            )
    );

    public static final Block YELLOW_WILLOW_SAPLING = registerBlock("yellow_willow_sapling",
            new SaplingBlock(TerrarianSaplingGenerator.YELLOW_WILLOW, AbstractBlock.Settings.create()
                    .mapColor(MapColor.YELLOW)
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.CHERRY_SAPLING)
                    .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block POTTED_YELLOW_WILLOW_SAPLING = registerItemlessBlock("potted_yellow_willow_sapling",
            Blocks.createFlowerPotBlock(YELLOW_WILLOW_SAPLING)
    );

    public static final Block YELLOW_WILLOW_PLANKS = registerBlock("yellow_willow_planks",
            new Block(AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.CHERRY_WOOD).burnable()
            )
    );
    public static final Block YELLOW_WILLOW_STAIRS = registerBlock("yellow_willow_stairs",
            new StairsBlock(YELLOW_WILLOW_PLANKS.getDefaultState(), AbstractBlock.Settings.create()
                    .mapColor(YELLOW_WILLOW_PLANKS.getDefaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.CHERRY_WOOD).burnable())
    );
    public static final Block YELLOW_WILLOW_SLAB = registerBlock("yellow_willow_slab",
            new SlabBlock(AbstractBlock.Settings.create()
                    .mapColor(YELLOW_WILLOW_PLANKS.getDefaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.CHERRY_WOOD).burnable())
    );
    public static final Block YELLOW_WILLOW_BUTTON = registerBlock("yellow_willow_button",
            Blocks.createWoodenButtonBlock(TerrarianBlockSetType.YELLOW_WILLOW)
    );
    public static final Block YELLOW_WILLOW_PRESSURE_PLATE = registerBlock("yellow_willow_pressure_plate",
            new PressurePlateBlock(TerrarianBlockSetType.YELLOW_WILLOW, AbstractBlock.Settings.create()
                    .mapColor(YELLOW_WILLOW_PLANKS.getDefaultMapColor())
                    .solid()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(0.5F)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block YELLOW_WILLOW_FENCE = registerBlock("yellow_willow_fence",
            new FenceBlock(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_YELLOW)
                    .mapColor(YELLOW_WILLOW_PLANKS.getDefaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.CHERRY_WOOD).burnable()
            )
    );
    public static final Block YELLOW_WILLOW_FENCE_GATE = registerBlock("yellow_willow_fence_gate",
            new FenceGateBlock(TerrarianWoodType.YELLOW_WILLOW, AbstractBlock.Settings.create()
                    .mapColor(YELLOW_WILLOW_PLANKS.getDefaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.CHERRY_WOOD).burnable()
            )
    );
    public static final Block YELLOW_WILLOW_DOOR = registerBlock("yellow_willow_door",
            new DoorBlock(TerrarianBlockSetType.YELLOW_WILLOW, AbstractBlock.Settings.create()
                    .mapColor(YELLOW_WILLOW_PLANKS.getDefaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .nonOpaque()
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block YELLOW_WILLOW_TRAPDOOR = registerBlock("yellow_willow_trapdoor",
            new TrapdoorBlock(TerrarianBlockSetType.YELLOW_WILLOW, AbstractBlock.Settings.create()
                    .mapColor(YELLOW_WILLOW_PLANKS.getDefaultMapColor())
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(3.0F)
                    .nonOpaque()
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
            )
    );
    public static final Block YELLOW_WILLOW_SIGN = registerItemlessBlock("yellow_willow_sign",
            new SignBlock(TerrarianWoodType.YELLOW_WILLOW, AbstractBlock.Settings.create()
                    .mapColor(YELLOW_WILLOW_PLANKS.getDefaultMapColor())
                    .solid()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .burnable()
            )
    );
    public static final Block YELLOW_WILLOW_WALL_SIGN = registerItemlessBlock("yellow_willow_wall_sign",
            new WallSignBlock(TerrarianWoodType.YELLOW_WILLOW, AbstractBlock.Settings.create()
                    .mapColor(YELLOW_WILLOW_SIGN.getDefaultMapColor())
                    .solid()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .dropsLike(YELLOW_WILLOW_SIGN)
                    .burnable()
            )
    );
    public static final Block YELLOW_WILLOW_HANGING_SIGN = registerItemlessBlock("yellow_willow_hanging_sign",
            new HangingSignBlock(TerrarianWoodType.YELLOW_WILLOW, AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW)
                    .solid()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .burnable()
            )
    );
    public static final Block YELLOW_WILLOW_WALL_HANGING_SIGN = registerItemlessBlock("yellow_willow_wall_hanging_sign",
            new WallHangingSignBlock(TerrarianWoodType.YELLOW_WILLOW, AbstractBlock.Settings.create()
                    .mapColor(YELLOW_WILLOW_HANGING_SIGN.getDefaultMapColor())
                    .solid()
                    .instrument(NoteBlockInstrument.BASS)
                    .noCollision()
                    .strength(1.0F)
                    .dropsLike(YELLOW_WILLOW_HANGING_SIGN)
                    .burnable()
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
            entries.addAfter(Blocks.AMETHYST_CLUSTER,
                    BUDDING_LIFE_CRYSTAL,
                    DEEPSLATE_BUDDING_LIFE_CRYSTAL,
                    SMALL_LIFE_CRYSTAL_BUD,
                    MEDIUM_LIFE_CRYSTAL_BUD,
                    LARGE_LIFE_CRYSTAL_BUD,
                    LIFE_CRYSTAL_CLUSTER
            );
            entries.addBefore(Blocks.AZALEA, YELLOW_WILLOW_SAPLING);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Blocks.AMETHYST_BLOCK, LIFE_CRYSTAL_BLOCK);
            entries.addBefore(Blocks.BAMBOO_BLOCK,
                    YELLOW_WILLOW_LOG,
                    YELLOW_WILLOW_WOOD,
                    STRIPPED_YELLOW_WILLOW_LOG,
                    STRIPPED_YELLOW_WILLOW_WOOD,
                    YELLOW_WILLOW_PLANKS,
                    YELLOW_WILLOW_STAIRS,
                    YELLOW_WILLOW_SLAB,
                    YELLOW_WILLOW_FENCE,
                    YELLOW_WILLOW_FENCE_GATE,
                    YELLOW_WILLOW_DOOR,
                    YELLOW_WILLOW_TRAPDOOR,
                    YELLOW_WILLOW_PRESSURE_PLATE,
                    YELLOW_WILLOW_BUTTON
            );
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries ->
                entries.addAfter(Blocks.SOUL_LANTERN,
                HEART_LANTERN,
                CHIPPED_HEART_LANTERN,
                DAMAGED_HEART_LANTERN,
                BROKEN_HEART_LANTERN
        ));
    }
}
