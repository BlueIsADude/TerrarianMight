package net.bluethedude.terrarianmight;

import eu.midnightdust.lib.config.MidnightConfig;

public class TerrarianConfig extends MidnightConfig {
    public static final String HEALTHY_DOSE = "healthyDose";

    @Comment(category = HEALTHY_DOSE, centered = true) public static Comment lifeCrystals;
    @Entry(category = HEALTHY_DOSE, min = 1) public static int crystalHealthGain = 2;
    @Entry(category = HEALTHY_DOSE, min = 20) public static int crystalMaxHealth = 40;
    @Entry(category = HEALTHY_DOSE, min = 0) public static int crystalCooldown = 14;

    @Comment(category = HEALTHY_DOSE, centered = true) public static Comment buddingLifeCrystals;
    @Entry(category = HEALTHY_DOSE, min = 0) public static int crystalGrowthChance = 2;

    @Comment(category = HEALTHY_DOSE, centered = true) public static Comment lifeFruit;
    @Entry(category = HEALTHY_DOSE) public static boolean fruitNeedsMaxHealth = false;
    @Entry(category = HEALTHY_DOSE, min = 0) public static int fruitCooldown = 600;
    @Entry(category = HEALTHY_DOSE, min = 0) public static int fruitDuration = 800;
    @Entry(category = HEALTHY_DOSE, min = 0, max = 255) public static int fruitAmplifier = 4;

    @Comment(category = HEALTHY_DOSE, centered = true) public static Comment heartLanterns;
    @Entry(category = HEALTHY_DOSE) public static boolean lanternRepair = true;
    @Entry(category = HEALTHY_DOSE, min = 0) public static int lanternDuration = 400;
    @Entry(category = HEALTHY_DOSE, min = 0, max = 255) public static int lanternAmplifier = 0;
    @Entry(category = HEALTHY_DOSE) public static LanternScalesEnum lanternScales = LanternScalesEnum.TRUE;
    public enum LanternScalesEnum {
        TRUE, INVERTED, FALSE
    }
}
