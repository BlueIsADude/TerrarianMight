package net.bluethedude.terrarianmight.datagen;

import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class TerrarianLootTableProvider extends FabricBlockLootTableProvider {
    public TerrarianLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {

        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);

        addDrop(TerrarianBlocks.BUDDING_LIFE_CRYSTAL,
                block -> this.dropsWithSilkTouch(block, ItemEntry.builder(TerrarianItems.LIFE_SHARD)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                        .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .alternatively(
                                ItemEntry.builder(TerrarianItems.MUSIC_DISC_HEARTFELT)
                                        .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.025F, 0.05F, 0.1F, 0.25F))
                        )
                )
        );
        addDrop(TerrarianBlocks.DEEPSLATE_BUDDING_LIFE_CRYSTAL,
                block -> this.dropsWithSilkTouch(block, ItemEntry.builder(TerrarianItems.LIFE_SHARD)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                        .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .alternatively(
                                ItemEntry.builder(TerrarianItems.MUSIC_DISC_HEARTFELT)
                                        .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.025F, 0.05F, 0.1F, 0.25F))
                        )
                )
        );

        addDrop(TerrarianBlocks.SMALL_LIFE_CRYSTAL_BUD, dropsWithSilkTouch(TerrarianBlocks.SMALL_LIFE_CRYSTAL_BUD));
        addDrop(TerrarianBlocks.MEDIUM_LIFE_CRYSTAL_BUD, dropsWithSilkTouch(TerrarianBlocks.MEDIUM_LIFE_CRYSTAL_BUD));
        addDrop(TerrarianBlocks.LARGE_LIFE_CRYSTAL_BUD, dropsWithSilkTouch(TerrarianBlocks.LARGE_LIFE_CRYSTAL_BUD));
        addDrop(TerrarianBlocks.LIFE_CRYSTAL_CLUSTER,
                block -> this.dropsWithSilkTouch(block, ItemEntry.builder(TerrarianItems.LIFE_SHARD)
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(4.0F)))
                        .conditionally(MatchToolLootCondition.builder(ItemPredicate.Builder.create().tag(ItemTags.CLUSTER_MAX_HARVESTABLES)))
                        .alternatively(
                                this.applyExplosionDecay(
                                        block, ItemEntry.builder(TerrarianItems.LIFE_SHARD).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(2.0F)))
                                )
                        )
                )
        );

        addDrop(TerrarianBlocks.LIFE_CRYSTAL_BLOCK, drops(TerrarianBlocks.LIFE_CRYSTAL_BLOCK));

        addDrop(TerrarianBlocks.HEART_LANTERN, drops(TerrarianBlocks.HEART_LANTERN));
        addDrop(TerrarianBlocks.CHIPPED_HEART_LANTERN, drops(TerrarianBlocks.CHIPPED_HEART_LANTERN));
        addDrop(TerrarianBlocks.DAMAGED_HEART_LANTERN, drops(TerrarianBlocks.DAMAGED_HEART_LANTERN));
        addDrop(TerrarianBlocks.BROKEN_HEART_LANTERN, drops(TerrarianBlocks.BROKEN_HEART_LANTERN));
    }
}
