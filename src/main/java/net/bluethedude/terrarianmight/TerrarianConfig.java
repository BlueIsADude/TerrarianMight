package net.bluethedude.terrarianmight;

import eu.midnightdust.lib.config.MidnightConfig;

public class TerrarianConfig extends MidnightConfig {
    public static final String TERRARIAN_MIGHT = "terrarianMight";

    @Comment(category = TERRARIAN_MIGHT, centered = true) public static Comment lifeCrystals;
    @Entry(category = TERRARIAN_MIGHT) public static boolean crystalPermanentHealth = false;
    @Entry(category = TERRARIAN_MIGHT, min = 1) public static int crystalHealthGain = 2;
    @Entry(category = TERRARIAN_MIGHT, min = 0) public static int crystalMaxHealth = 20;
    @Entry(category = TERRARIAN_MIGHT, min = 0) public static int crystalCooldown = 14;

    @Comment(category = TERRARIAN_MIGHT, centered = true) public static Comment buddingLifeCrystals;
    @Entry(category = TERRARIAN_MIGHT, min = 0) public static int crystalGrowthChance = 2;

    @Comment(category = TERRARIAN_MIGHT, centered = true) public static Comment lifeFruit;
    @Entry(category = TERRARIAN_MIGHT) public static boolean fruitNeedsMaxHealth = false;
    @Entry(category = TERRARIAN_MIGHT, min = 0) public static int fruitCooldown = 500;
    @Entry(category = TERRARIAN_MIGHT, min = 0) public static int fruitDuration = 600;
    @Entry(category = TERRARIAN_MIGHT, min = 0, max = 255) public static int fruitAmplifier = 4;

    @Comment(category = TERRARIAN_MIGHT, centered = true) public static Comment heartLanterns;
    @Entry(category = TERRARIAN_MIGHT) public static boolean lanternRepair = true;
    @Entry(category = TERRARIAN_MIGHT, min = 0) public static int lanternDuration = 400;
    @Entry(category = TERRARIAN_MIGHT, min = 0, max = 255) public static int lanternAmplifier = 0;
    @Entry(category = TERRARIAN_MIGHT) public static LanternScalesEnum lanternScales = LanternScalesEnum.TRUE;
    public enum LanternScalesEnum {
        TRUE, INVERTED, FALSE
    }

    @Comment(category = TERRARIAN_MIGHT, centered = true) public static Comment magic;
    @Entry(category = TERRARIAN_MIGHT) public static boolean passiveManaGain = true;
    @Condition(requiredOption = "passiveManaGain", requiredValue = "true")
    @Entry(category = TERRARIAN_MIGHT) public static int manaGainTimer = 60;

    @Comment(category = TERRARIAN_MIGHT, centered = true) public static Comment miscOptions;
    @Entry(category = TERRARIAN_MIGHT) public static boolean creativeTab = true;
    @Entry(category = TERRARIAN_MIGHT, min = 0) public static int baseHealth = 20;
}
