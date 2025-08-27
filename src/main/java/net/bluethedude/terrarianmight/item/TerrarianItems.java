package net.bluethedude.terrarianmight.item;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.entity.TerrarianBoats;
import net.bluethedude.terrarianmight.item.custom.*;
import net.bluethedude.terrarianmight.sound.TerrarianJukeboxSongs;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.*;
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

    public static final Item AMETHYST_STAFF = registerItem("amethyst_staff",
            new AmethystStaffItem(new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.RARE)
                    .attributeModifiers(AmethystStaffItem.createAttributeModifiers())
                    .component(DataComponentTypes.TOOL, AmethystStaffItem.createToolComponent())
            )
    );
    public static final Item ZEPHYR_SCEPTER = registerItem("zephyr_scepter",
            new ZephyrScepterItem(new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.EPIC)
                    .attributeModifiers(ZephyrScepterItem.createAttributeModifiers())
                    .component(DataComponentTypes.TOOL, ZephyrScepterItem.createToolComponent())
            )
    );
    public static final Item PORK_STORM = registerItem("pork_storm",
            new PorkStormItem(new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.EPIC)
                    .attributeModifiers(PorkStormItem.createAttributeModifiers())
                    .component(DataComponentTypes.TOOL, PorkStormItem.createToolComponent())
            )
    );
    public static final Item SLIME_STAFF = registerItem("slime_staff",
            new SlimeStaffItem(new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.RARE)
                    .attributeModifiers(SlimeStaffItem.createAttributeModifiers())
                    .component(DataComponentTypes.TOOL, SlimeStaffItem.createToolComponent())
            )
    );
    public static final Item OPTIC_STAFF = registerItem("optic_staff",
            new OpticStaffItem(new Item.Settings()
                    .maxCount(1)
                    .rarity(Rarity.EPIC)
                    .attributeModifiers(OpticStaffItem.createAttributeModifiers())
                    .component(DataComponentTypes.TOOL, OpticStaffItem.createToolComponent())
            )
    );

//    public static final Item MUSIC_DISC_HEARTFELT = registerItem("music_disc_heartfelt",
//            new Item(new Item.Settings()
//                    .maxCount(1)
//                    .rarity(Rarity.RARE)
//                    .jukeboxPlayable(TerrarianJukeboxSongs.HEARTFELT)
//            )
//    );

    public static final Item YELLOW_WILLOW_SIGN_ITEM = registerItem("yellow_willow_sign",
            new SignItem(new Item.Settings(), TerrarianBlocks.YELLOW_WILLOW_SIGN, TerrarianBlocks.YELLOW_WILLOW_WALL_SIGN));
    public static final Item YELLOW_WILLOW_HANGING_SIGN_ITEM = registerItem("yellow_willow_hanging_sign",
            new HangingSignItem(TerrarianBlocks.YELLOW_WILLOW_HANGING_SIGN, TerrarianBlocks.YELLOW_WILLOW_WALL_HANGING_SIGN, new Item.Settings()));

    public static final Item YELLOW_WILLOW_BOAT = TerraformBoatItemHelper.registerBoatItem(
            TerrarianBoats.YELLOW_WILLOW_BOAT_ID,
            TerrarianBoats.YELLOW_WILLOW_BOAT_KEY,
            false
    );
    public static final Item YELLOW_WILLOW_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(
            TerrarianBoats.YELLOW_WILLOW_CHEST_BOAT_ID,
            TerrarianBoats.YELLOW_WILLOW_BOAT_KEY,
            true
    );


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(TerrarianMight.MOD_ID, name), item);
    }

    public static void registerTerrarianItems() {
        TerrarianMight.LOGGER.info("Registering Mod Items for " + TerrarianMight.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries ->
                entries.addBefore(Items.BAMBOO_SIGN, YELLOW_WILLOW_SIGN_ITEM, YELLOW_WILLOW_HANGING_SIGN_ITEM));
//        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries ->
//                entries.add(MUSIC_DISC_HEARTFELT));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->
                entries.addAfter(Items.AMETHYST_SHARD, LIFE_SHARD));
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
                entries.addAfter(Items.MACE,
                        AMETHYST_STAFF,
                        ZEPHYR_SCEPTER,
                        SLIME_STAFF,
                        OPTIC_STAFF
                );
                entries.addAfter(Items.TOTEM_OF_UNDYING, LIFE_CRYSTAL);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries ->
                entries.addAfter(Items.GLOW_BERRIES, LIFE_FRUIT));
    }
}
