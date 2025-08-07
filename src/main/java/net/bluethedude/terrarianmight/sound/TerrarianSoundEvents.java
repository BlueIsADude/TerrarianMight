package net.bluethedude.terrarianmight.sound;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class TerrarianSoundEvents {

    public static final SoundEvent ITEM_LIFE_CRYSTAL_USE = registerSoundEvent("item.life_crystal.use");
    public static final SoundEvent ITEM_LIFE_FRUIT_FINISH = registerSoundEvent("item.life_fruit.finish");

    public static final SoundEvent BLOCK_HEART_LANTERN_CRACK1 = registerSoundEvent("block.heart_lantern.crack1");
    public static final SoundEvent BLOCK_HEART_LANTERN_CRACK2 = registerSoundEvent("block.heart_lantern.crack2");
    public static final SoundEvent BLOCK_HEART_LANTERN_CRACK3 = registerSoundEvent("block.heart_lantern.crack3");
    public static final SoundEvent BLOCK_HEART_LANTERN_REPAIR = registerSoundEvent("block.heart_lantern.repair");

    public static final SoundEvent MUSIC_DISC_HEARTFELT = registerSoundEvent("music_disc.heartfelt");

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(TerrarianMight.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSoundEvents() {
        TerrarianMight.LOGGER.info("Registering Mod Sounds for " + TerrarianMight.MOD_ID);
    }
}
