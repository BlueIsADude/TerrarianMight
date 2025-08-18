package net.bluethedude.terrarianmight.block;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.block.WoodType;
import net.minecraft.util.Identifier;


public class TerrarianWoodType {
    public static final WoodType YELLOW_WILLOW = new WoodTypeBuilder().register(Identifier.of(TerrarianMight.MOD_ID, "yellow_willow"), TerrarianBlockSetType.YELLOW_WILLOW);
}
