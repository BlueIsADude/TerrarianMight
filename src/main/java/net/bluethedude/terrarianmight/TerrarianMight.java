package net.bluethedude.terrarianmight;

import eu.midnightdust.lib.config.MidnightConfig;
import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.entity.TerrarianBoats;
import net.bluethedude.terrarianmight.entity.TerrarianEntityTypes;
import net.bluethedude.terrarianmight.entity.custom.RetinazerEntity;
import net.bluethedude.terrarianmight.entity.custom.SlimeWolfEntity;
import net.bluethedude.terrarianmight.entity.custom.SpazmatismEntity;
import net.bluethedude.terrarianmight.item.TerrarianItemGroups;
import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.bluethedude.terrarianmight.particle.TerrarianParticleTypes;
import net.bluethedude.terrarianmight.sound.TerrarianJukeboxSongs;
import net.bluethedude.terrarianmight.sound.TerrarianSoundEvents;
import net.bluethedude.terrarianmight.util.TerrarianDataComponents;
import net.bluethedude.terrarianmight.util.TerrarianHealthManager;
import net.bluethedude.terrarianmight.util.TerrarianLootTableModifiers;
import net.bluethedude.terrarianmight.world.gen.TerrarianWorldGeneration;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityType;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerrarianMight implements ModInitializer {

    public static final String MOD_ID = "terrarianmight";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        TerrarianItems.registerTerrarianItems();
        TerrarianBlocks.registerTerrarianBlocks();
        TerrarianEntityTypes.registerTerrarianEntities();
        TerrarianBoats.registerTerrarianBoats();

        if (TerrarianConfig.creativeTab) {
            TerrarianItemGroups.registerItemGroups();
        }

        TerrarianSoundEvents.registerSoundEvents();
        TerrarianJukeboxSongs.registerJukeboxSongs();
        TerrarianParticleTypes.registerParticles();

        TerrarianWorldGeneration.generateTerrarianWorldGen();

        TerrarianDataComponents.registerDataComponentTypes();
        TerrarianHealthManager.registerHealthManager();
        TerrarianLootTableModifiers.modifyLootTables();

        StrippableBlockRegistry.register(TerrarianBlocks.YELLOW_WILLOW_LOG, TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG);
        StrippableBlockRegistry.register(TerrarianBlocks.YELLOW_WILLOW_WOOD, TerrarianBlocks.STRIPPED_YELLOW_WILLOW_WOOD);

        FabricBlockEntityType signEntity = BlockEntityType.SIGN;
        signEntity.addSupportedBlock(TerrarianBlocks.YELLOW_WILLOW_SIGN);
        signEntity.addSupportedBlock(TerrarianBlocks.YELLOW_WILLOW_WALL_SIGN);

        FabricBlockEntityType hangingSignEntity = BlockEntityType.HANGING_SIGN;
        hangingSignEntity.addSupportedBlock(TerrarianBlocks.YELLOW_WILLOW_HANGING_SIGN);
        hangingSignEntity.addSupportedBlock(TerrarianBlocks.YELLOW_WILLOW_WALL_HANGING_SIGN);

        FlammableBlockRegistry.getDefaultInstance().add(TerrarianBlocks.YELLOW_WILLOW_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(TerrarianBlocks.YELLOW_WILLOW_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(TerrarianBlocks.STRIPPED_YELLOW_WILLOW_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(TerrarianBlocks.YELLOW_WILLOW_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(TerrarianBlocks.YELLOW_WILLOW_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(TerrarianBlocks.YELLOW_WILLOW_STAIRS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(TerrarianBlocks.YELLOW_WILLOW_SLAB, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(TerrarianBlocks.YELLOW_WILLOW_FENCE, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(TerrarianBlocks.YELLOW_WILLOW_FENCE_GATE, 5, 20);

        FabricDefaultAttributeRegistry.register(TerrarianEntityTypes.SLIME_WOLF, SlimeWolfEntity.createSummonAttributes());
        FabricDefaultAttributeRegistry.register(TerrarianEntityTypes.SPAZ, SpazmatismEntity.createSummonAttributes());
        FabricDefaultAttributeRegistry.register(TerrarianEntityTypes.REZ, RetinazerEntity.createSummonAttributes());

        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 5),
                    new ItemStack(TerrarianBlocks.YELLOW_WILLOW_SAPLING, 1), 8,1,0.04f
            ));
        });

        MidnightConfig.init(MOD_ID, TerrarianConfig.class);
    }
}
