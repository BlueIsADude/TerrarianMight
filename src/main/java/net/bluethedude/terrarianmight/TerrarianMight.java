package net.bluethedude.terrarianmight;

import eu.midnightdust.lib.config.MidnightConfig;
import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.item.TerrarianItemGroups;
import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.bluethedude.terrarianmight.sound.TerrarianJukeboxSongs;
import net.bluethedude.terrarianmight.sound.TerrarianSoundEvents;
import net.bluethedude.terrarianmight.util.misc.TerrarianHealthManager;
import net.bluethedude.terrarianmight.world.gen.TerrarianWorldGeneration;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerrarianMight implements ModInitializer {

    public static final String MOD_ID = "terrarianmight";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        TerrarianItems.registerTerrarianItems();
        TerrarianBlocks.registerTerrarianBlocks();

        if (TerrarianConfig.creativeTab) {
            TerrarianItemGroups.registerItemGroups();
        }

        TerrarianSoundEvents.registerSoundEvents();
        TerrarianJukeboxSongs.registerJukeboxSongs();

        TerrarianWorldGeneration.generateTerrarianWorldGen();

        TerrarianHealthManager.registerHealthManager();

        MidnightConfig.init(MOD_ID, TerrarianConfig.class);
    }
}
