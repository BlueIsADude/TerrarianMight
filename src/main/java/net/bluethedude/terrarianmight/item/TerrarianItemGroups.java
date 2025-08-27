package net.bluethedude.terrarianmight.item;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class TerrarianItemGroups {
    public static final ItemGroup TERRARIAN_MIGHT_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TerrarianMight.MOD_ID, "terrarian_might"),
            FabricItemGroup.builder().icon(() -> new ItemStack(TerrarianBlocks.YELLOW_WILLOW_SAPLING))
                    .displayName(Text.translatable("itemgroup.terrarianmight.title"))
                    .entries((displayContext, entries) -> {
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_SAPLING);
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_LEAVES);
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_LOG);
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_WOOD);
                        entries.add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG);
                        entries.add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_WOOD);
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_PLANKS);
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_STAIRS);
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_SLAB);
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_FENCE);
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_FENCE_GATE);
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_DOOR);
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_TRAPDOOR);
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_PRESSURE_PLATE);
                        entries.add(TerrarianBlocks.YELLOW_WILLOW_BUTTON);
                        entries.add(TerrarianItems.YELLOW_WILLOW_SIGN_ITEM);
                        entries.add(TerrarianItems.YELLOW_WILLOW_HANGING_SIGN_ITEM);
                        entries.add(TerrarianItems.YELLOW_WILLOW_BOAT);
                        entries.add(TerrarianItems.YELLOW_WILLOW_CHEST_BOAT);
                        entries.add(TerrarianBlocks.BUDDING_LIFE_CRYSTAL);
                        entries.add(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL);
                        entries.add(TerrarianBlocks.SMALL_LIFE_CRYSTAL_BUD);
                        entries.add(TerrarianBlocks.MEDIUM_LIFE_CRYSTAL_BUD);
                        entries.add(TerrarianBlocks.LARGE_LIFE_CRYSTAL_BUD);
                        entries.add(TerrarianBlocks.LIFE_CRYSTAL_CLUSTER);
                        entries.add(TerrarianItems.LIFE_SHARD);
                        entries.add(TerrarianItems.LIFE_CRYSTAL);
                        entries.add(TerrarianBlocks.LIFE_CRYSTAL_BLOCK);
                        entries.add(TerrarianBlocks.HEART_LANTERN);
                        entries.add(TerrarianBlocks.CHIPPED_HEART_LANTERN);
                        entries.add(TerrarianBlocks.DAMAGED_HEART_LANTERN);
                        entries.add(TerrarianBlocks.BROKEN_HEART_LANTERN);
                        entries.add(TerrarianItems.LIFE_FRUIT);
                        entries.add(TerrarianItems.AMETHYST_STAFF);
                        entries.add(TerrarianItems.ZEPHYR_SCEPTER);
                        entries.add(TerrarianItems.SLIME_STAFF);
                        entries.add(TerrarianItems.OPTIC_STAFF);
//                        entries.add(TerrarianItems.MUSIC_DISC_HEARTFELT);
                    }).build());

    public static void registerItemGroups() {}
}
