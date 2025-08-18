package net.bluethedude.terrarianmight.entity;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class TerrarianBoats {

    public static final Identifier YELLOW_WILLOW_BOAT_ID = Identifier.of(TerrarianMight.MOD_ID, "yellow_willow_boat");
    public static final Identifier YELLOW_WILLOW_CHEST_BOAT_ID = Identifier.of(TerrarianMight.MOD_ID, "yellow_willow_chest_boat");

    public static final RegistryKey<TerraformBoatType> YELLOW_WILLOW_BOAT_KEY = TerraformBoatTypeRegistry.createKey(YELLOW_WILLOW_BOAT_ID);

    public static void registerTerrarianBoats() {
        TerraformBoatType yellowWillowBoat = new TerraformBoatType.Builder()
                .item(TerrarianItems.YELLOW_WILLOW_BOAT)
                .chestItem(TerrarianItems.YELLOW_WILLOW_CHEST_BOAT)
                .planks(TerrarianBlocks.YELLOW_WILLOW_PLANKS.asItem())
                .build();
        Registry.register(TerraformBoatTypeRegistry.INSTANCE, YELLOW_WILLOW_BOAT_KEY, yellowWillowBoat);
    }
}
