package net.bluethedude.terrarianmight.criterion;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.criterion.custom.SummonMinionCriterion;
import net.minecraft.advancement.criterion.Criteria;

public class TerrarianCriteria {
    public static SummonMinionCriterion SUMMON_MINION = Criteria.register(TerrarianMight.MOD_ID + ":summon_minion", new SummonMinionCriterion());

    public static void registerTerrarianCriteria() {
        TerrarianMight.LOGGER.info("Registering Mod Criteria for " + TerrarianMight.MOD_ID);
    }
}
