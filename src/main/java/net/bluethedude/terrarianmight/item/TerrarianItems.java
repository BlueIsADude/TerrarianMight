package net.bluethedude.terrarianmight.item;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.item.custom.LifeCrystalItem;
import net.bluethedude.terrarianmight.item.custom.LifeFruitItem;
import net.bluethedude.terrarianmight.sound.TerrarianJukeboxSongs;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class TerrarianItems {

    public static final Item LIFE_SHARD = registerItem("life_shard",
            new Item(new Item.Settings()));
    public static final Item LIFE_CRYSTAL = registerItem("life_crystal",
            new LifeCrystalItem(new Item.Settings()
                    .rarity(Rarity.RARE)
            )
    );
    public static final Item LIFE_FRUIT = registerItem("life_fruit",
            new LifeFruitItem(new Item.Settings()
                    .rarity(Rarity.RARE)
                    .food(TerrarianFoodComponents.LIFE_FRUIT)
            )
    );
    public static final Item MUSIC_DISC_HEARTFELT = registerItem("music_disc_heartfelt",
            new Item(new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.RARE)
                    .jukeboxPlayable(TerrarianJukeboxSongs.HEARTFELT)
            )
    );

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TerrarianMight.MOD_ID, name), item);
    }

    public static void registerTerrarianItems() {
        TerrarianMight.LOGGER.info("Registering Mod Items for " + TerrarianMight.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries ->
                entries.add(MUSIC_DISC_HEARTFELT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->
                entries.addAfter(Items.AMETHYST_SHARD, LIFE_SHARD));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries ->
                entries.addAfter(Items.TOTEM_OF_UNDYING, LIFE_CRYSTAL));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries ->
                entries.addAfter(Items.GLOW_BERRIES, LIFE_FRUIT));
    }
}
