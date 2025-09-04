package net.bluethedude.terrarianmight.util;

import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.SlimePredicate;
import net.minecraft.util.Identifier;

public class TerrarianLootTableModifiers {
    private static final Identifier MINESHAFT_ID =
            Identifier.ofVanilla("chests/abandoned_mineshaft");
    private static final Identifier DUNGEON_ID =
            Identifier.ofVanilla("chests/simple_dungeon");
    private static final Identifier JUNGLE_TEMPLE_ID =
            Identifier.ofVanilla("chests/jungle_temple");
    private static final Identifier VAULT_ID =
            Identifier.ofVanilla("chests/trial_chambers/reward_unique");
    private static final Identifier END_CITY_ID =
            Identifier.ofVanilla("chests/end_city_treasure");
    private static final Identifier SLIME_ID =
            Identifier.ofVanilla("entities/slime");

    public static void modifyLootTables(){
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (MINESHAFT_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f))
                        .with(ItemEntry.builder(TerrarianItems.WAND_OF_SPARKING))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build());
                tableBuilder.pool(poolBuilder);
                poolBuilder.build();
            }
            if (DUNGEON_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.05f))
                        .with(ItemEntry.builder(TerrarianItems.WAND_OF_SPARKING))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build());
                tableBuilder.pool(poolBuilder);
                poolBuilder.build();
            }
            if (JUNGLE_TEMPLE_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(TerrarianItems.WAND_OF_SPARKING))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build());
                tableBuilder.pool(poolBuilder);
                poolBuilder.build();
            }
            if (VAULT_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.1f))
                        .with(ItemEntry.builder(TerrarianItems.ZEPHYR_SCEPTER))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build());
                tableBuilder.pool(poolBuilder);
                poolBuilder.build();
            }
            if (END_CITY_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(0.025f))
                        .with(ItemEntry.builder(TerrarianItems.OPTIC_STAFF))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build());
                tableBuilder.pool(poolBuilder);
                poolBuilder.build();
            }
            if (SLIME_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(TerrarianItems.SLIME_STAFF))
                        .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1)).build())
                        .conditionally(
                                EntityPropertiesLootCondition.builder(
                                        LootContext.EntityTarget.THIS, EntityPredicate.Builder.create().typeSpecific(SlimePredicate.of(NumberRange.IntRange.exactly(1)))
                                )
                        )
                        .conditionally(RandomChanceLootCondition.builder(0.025f));
                tableBuilder.pool(poolBuilder);
                poolBuilder.build();
            }
        });
    }
}
