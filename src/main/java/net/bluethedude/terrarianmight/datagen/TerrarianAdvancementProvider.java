package net.bluethedude.terrarianmight.datagen;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.criterion.custom.SummonMinionCriterion;
import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.bluethedude.terrarianmight.util.TerrarianTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.*;
import net.minecraft.data.server.advancement.AdvancementTabGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.BlockPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class TerrarianAdvancementProvider extends FabricAdvancementProvider {

    public TerrarianAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup wrapperLookup, Consumer<AdvancementEntry> consumer) {

        AdvancementEntry lifeShard = Advancement.Builder.create()
                .parent(AdvancementTabGenerator.reference("adventure/root"))
                .display(
                        TerrarianItems.LIFE_SHARD,
                        Text.translatable("advancements.terrarianmight.get_life_shard.title"),
                        Text.translatable("advancements.terrarianmight.get_life_shard.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("get_life_shard", InventoryChangedCriterion.Conditions.items(TerrarianItems.LIFE_SHARD))
                .build(consumer, TerrarianMight.MOD_ID + ":adventure/get_life_shard");

        AdvancementEntry lifeCrystal = Advancement.Builder.create()
                .parent(lifeShard)
                .display(
                        TerrarianItems.LIFE_CRYSTAL,
                        Text.translatable("advancements.terrarianmight.use_life_crystal.title"),
                        Text.translatable("advancements.terrarianmight.use_life_crystal.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("use_life_crystal",
                        UsingItemCriterion.Conditions.create(
                                EntityPredicate.Builder.create().type(EntityType.PLAYER),
                                ItemPredicate.Builder.create().items(TerrarianItems.LIFE_CRYSTAL)
                        )
                )
                .build(consumer, TerrarianMight.MOD_ID + ":adventure/use_life_crystal");

        AdvancementEntry lifeFruit = Advancement.Builder.create()
                .parent(lifeCrystal)
                .display(
                        TerrarianItems.LIFE_FRUIT,
                        Text.translatable("advancements.terrarianmight.eat_life_fruit.title"),
                        Text.translatable("advancements.terrarianmight.eat_life_fruit.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("eat_life_fruit", ConsumeItemCriterion.Conditions.item(TerrarianItems.LIFE_FRUIT))
                .build(consumer, TerrarianMight.MOD_ID + ":adventure/eat_life_fruit");

        AdvancementEntry repairHeartLantern = Advancement.Builder.create()
                .parent(lifeShard)
                .display(
                        TerrarianBlocks.HEART_LANTERN,
                        Text.translatable("advancements.terrarianmight.repair_heart_lantern.title"),
                        Text.translatable("advancements.terrarianmight.repair_heart_lantern.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("repair_heart_lantern", ItemCriterion.Conditions.createItemUsedOnBlock(
                        LocationPredicate.Builder.create()
                                .block(BlockPredicate.Builder.create().blocks(
                                                TerrarianBlocks.CHIPPED_HEART_LANTERN,
                                                TerrarianBlocks.DAMAGED_HEART_LANTERN,
                                                TerrarianBlocks.BROKEN_HEART_LANTERN)
                                ),
                        ItemPredicate.Builder.create().items(TerrarianItems.LIFE_SHARD)
                ))
                .build(consumer, TerrarianMight.MOD_ID + ":adventure/repair_heart_lantern");

        AdvancementEntry getMagicItem = Advancement.Builder.create()
                .parent(AdvancementTabGenerator.reference("adventure/root"))
                .display(
                        TerrarianItems.AMETHYST_STAFF,
                        Text.translatable("advancements.terrarianmight.get_magic_item.title"),
                        Text.translatable("advancements.terrarianmight.get_magic_item.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("get_magic_item", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create().tag(TerrarianTags.Items.MANA_WEAPONS)
                ))
                .build(consumer, TerrarianMight.MOD_ID + ":adventure/get_magic_item");

        AdvancementEntry summonMinion = Advancement.Builder.create()
                .parent(getMagicItem)
                .display(
                        TerrarianItems.SLIME_STAFF,
                        Text.translatable("advancements.terrarianmight.summon_minion.title"),
                        Text.translatable("advancements.terrarianmight.summon_minion.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("summon_minion", SummonMinionCriterion.Conditions.any())
                .build(consumer, TerrarianMight.MOD_ID + ":adventure/summon_minion");
    }
}
