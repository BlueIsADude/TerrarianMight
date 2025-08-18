package net.bluethedude.terrarianmight.world.gen;

public class TerrarianWorldGeneration {
    public static void generateTerrarianWorldGen() {
        TerrarianOreGeneration.generateOres();
        TerrarianTreeGeneration.generateTrees();
    }
}
