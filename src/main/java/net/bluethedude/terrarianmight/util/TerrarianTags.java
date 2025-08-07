package net.bluethedude.terrarianmight.util;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TerrarianTags {
    public static class Blocks {
        public static final TagKey<Block> HEART_LANTERNS = createTag("heart_lanterns");
        public static final TagKey<Block> BUDDING_LIFE_CRYSTALS = createTag("budding_life_crystals");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(TerrarianMight.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> HEART_LANTERNS = createTag("heart_lanterns");
        public static final TagKey<Item> BUDDING_LIFE_CRYSTALS = createTag("budding_life_crystals");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(TerrarianMight.MOD_ID, name));
        }
    }
}
