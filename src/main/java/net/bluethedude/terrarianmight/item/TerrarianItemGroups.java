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
            FabricItemGroup.builder().icon(() -> new ItemStack(TerrarianItems.LIFE_CRYSTAL))
                    .displayName(Text.translatable("itemgroup.terrarianmight.title"))
                    .entries((displayContext, entries) -> {
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
                        entries.add(TerrarianItems.MUSIC_DISC_HEARTFELT);
                    }).build());

    public static void registerItemGroups() {}
}
