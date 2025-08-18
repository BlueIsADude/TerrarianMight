package net.bluethedude.terrarianmight.block;

import net.minecraft.block.BlockSetType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class TerrarianBlockSetType {
    public static final BlockSetType YELLOW_WILLOW = TerrarianBlockSetType.register(new BlockSetType("yellow_willow",
                    true,
                    true,
                    true,
                    BlockSetType.ActivationRule.EVERYTHING,
                    BlockSoundGroup.CHERRY_WOOD,
                    SoundEvents.BLOCK_CHERRY_WOOD_DOOR_CLOSE,
                    SoundEvents.BLOCK_CHERRY_WOOD_DOOR_OPEN,
                    SoundEvents.BLOCK_CHERRY_WOOD_TRAPDOOR_CLOSE,
                    SoundEvents.BLOCK_CHERRY_WOOD_TRAPDOOR_OPEN,
                    SoundEvents.BLOCK_CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF,
                    SoundEvents.BLOCK_CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON,
                    SoundEvents.BLOCK_CHERRY_WOOD_BUTTON_CLICK_OFF,
                    SoundEvents.BLOCK_CHERRY_WOOD_BUTTON_CLICK_ON
            )
    );

    private static BlockSetType register(BlockSetType blockSetType) {
        return blockSetType;
    }
}
