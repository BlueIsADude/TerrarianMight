package net.bluethedude.terrarianmight.world.tree;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.world.TerrarianConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class TerrarianSaplingGenerator {
    public static final SaplingGenerator YELLOW_WILLOW = new SaplingGenerator(TerrarianMight.MOD_ID + ":yellow_willow",
            Optional.empty(), Optional.of(TerrarianConfiguredFeatures.YELLOW_WILLOW), Optional.empty());
}
