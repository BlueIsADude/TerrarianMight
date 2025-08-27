package net.bluethedude.terrarianmight.util;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TerrarianTags {
    public static class Blocks {
        public static final TagKey<Block> YELLOW_WILLOW_LOGS = createTag("yellow_willow_logs");
        public static final TagKey<Block> HEART_LANTERNS = createTag("heart_lanterns");
        public static final TagKey<Block> BUDDING_LIFE_CRYSTALS = createTag("budding_life_crystals");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(TerrarianMight.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> YELLOW_WILLOW_LOGS = createTag("yellow_willow_logs");
        public static final TagKey<Item> HEART_LANTERNS = createTag("heart_lanterns");
        public static final TagKey<Item> BUDDING_LIFE_CRYSTALS = createTag("budding_life_crystals");
        public static final TagKey<Item> MAGIC_WEAPONS = createTag("magic_weapons");
        public static final TagKey<Item> MAGIC_ENCHANTABLE = createTag("magic_enchantable");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(TerrarianMight.MOD_ID, name));
        }
    }

    public static class Enchantments {
        public static final TagKey<Enchantment> MAGIC_EXCLUSIVE_SET = createTag("exclusive_set/magic_exclusive_set");

        private static TagKey<Enchantment> createTag(String name) {
            return TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(TerrarianMight.MOD_ID, name));
        }
    }
}
