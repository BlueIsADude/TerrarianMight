package net.bluethedude.terrarianmight.datagen;

import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.bluethedude.terrarianmight.util.TerrarianTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class TerrarianItemTagProvider extends FabricTagProvider.ItemTagProvider {

    public TerrarianItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.LOGS)
                .add(TerrarianBlocks.YELLOW_WILLOW_LOG.asItem())
                .add(TerrarianBlocks.YELLOW_WILLOW_WOOD.asItem())
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG.asItem())
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.LOGS_THAT_BURN)
                .add(TerrarianBlocks.YELLOW_WILLOW_LOG.asItem())
                .add(TerrarianBlocks.YELLOW_WILLOW_WOOD.asItem())
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG.asItem())
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_WOOD.asItem());

        getOrCreateTagBuilder(ConventionalItemTags.STRIPPED_WOODS)
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG.asItem())
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_WOOD.asItem());

        getOrCreateTagBuilder(TerrarianTags.Items.YELLOW_WILLOW_LOGS)
                .add(TerrarianBlocks.YELLOW_WILLOW_LOG.asItem())
                .add(TerrarianBlocks.YELLOW_WILLOW_WOOD.asItem())
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG.asItem())
                .add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_WOOD.asItem());

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(TerrarianBlocks.YELLOW_WILLOW_PLANKS.asItem());

        getOrCreateTagBuilder(ItemTags.BUTTONS)
                .add(TerrarianBlocks.YELLOW_WILLOW_BUTTON.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(TerrarianBlocks.YELLOW_WILLOW_BUTTON.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(TerrarianBlocks.YELLOW_WILLOW_PRESSURE_PLATE.asItem());

        getOrCreateTagBuilder(ItemTags.FENCES)
                .add(TerrarianBlocks.YELLOW_WILLOW_FENCE.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(TerrarianBlocks.YELLOW_WILLOW_FENCE.asItem());

        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(TerrarianBlocks.YELLOW_WILLOW_FENCE_GATE.asItem());

        getOrCreateTagBuilder(ItemTags.DOORS)
                .add(TerrarianBlocks.YELLOW_WILLOW_DOOR.asItem());

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(TerrarianBlocks.YELLOW_WILLOW_DOOR.asItem());

        getOrCreateTagBuilder(ItemTags.SIGNS)
                .add(TerrarianItems.YELLOW_WILLOW_SIGN_ITEM);

        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS)
                .add(TerrarianItems.YELLOW_WILLOW_HANGING_SIGN_ITEM);

        getOrCreateTagBuilder(TerrarianTags.Items.MAGIC_WEAPONS)
                .add(TerrarianItems.WAND_OF_SPARKING)
                .add(TerrarianItems.AMETHYST_STAFF)
                .add(TerrarianItems.ZEPHYR_SCEPTER);

        getOrCreateTagBuilder(TerrarianTags.Items.SUMMON_WEAPONS)
                .add(TerrarianItems.SLIME_STAFF)
                .add(TerrarianItems.OPTIC_STAFF);

        getOrCreateTagBuilder(TerrarianTags.Items.MANA_WEAPONS)
                .addTag(TerrarianTags.Items.MAGIC_WEAPONS)
                .addTag(TerrarianTags.Items.SUMMON_WEAPONS);

        getOrCreateTagBuilder(ConventionalItemTags.TOOLS)
                .addTag(TerrarianTags.Items.MANA_WEAPONS);

//        getOrCreateTagBuilder(ConventionalItemTags.MUSIC_DISCS)
//                .add(TerrarianItems.MUSIC_DISC_HEARTFELT);

        getOrCreateTagBuilder(ItemTags.TRIM_MATERIALS)
                .add(TerrarianItems.LIFE_SHARD);

        getOrCreateTagBuilder(TerrarianTags.Items.BUDDING_LIFE_CRYSTALS)
                .add(TerrarianBlocks.BUDDING_LIFE_CRYSTAL.asItem())
                .add(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL.asItem());

        getOrCreateTagBuilder(ConventionalItemTags.BUDDING_BLOCKS)
                .add(TerrarianBlocks.BUDDING_LIFE_CRYSTAL.asItem())
                .add(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL.asItem());

        getOrCreateTagBuilder(ConventionalItemTags.ORES)
                .add(TerrarianBlocks.BUDDING_LIFE_CRYSTAL.asItem())
                .add(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL.asItem());

        getOrCreateTagBuilder(TerrarianTags.Items.HEART_LANTERNS)
                .add(TerrarianBlocks.HEART_LANTERN.asItem())
                .add(TerrarianBlocks.CHIPPED_HEART_LANTERN.asItem())
                .add(TerrarianBlocks.DAMAGED_HEART_LANTERN.asItem())
                .add(TerrarianBlocks.BROKEN_HEART_LANTERN.asItem());
    }
}

