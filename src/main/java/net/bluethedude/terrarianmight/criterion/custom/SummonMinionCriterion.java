package net.bluethedude.terrarianmight.criterion.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.bluethedude.terrarianmight.criterion.TerrarianCriteria;
import net.bluethedude.terrarianmight.entity.custom.util.AbstractSummonEntity;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.loot.context.LootContext;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.entity.LootContextPredicateValidator;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

public class SummonMinionCriterion extends AbstractCriterion<SummonMinionCriterion.Conditions> {
    @Override
    public Codec<SummonMinionCriterion.Conditions> getConditionsCodec() {
        return SummonMinionCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player, AbstractSummonEntity entity) {
        LootContext lootContext = EntityPredicate.createAdvancementEntityLootContext(player, entity);
        this.trigger(player, conditions -> conditions.matches(lootContext));
    }

    public record Conditions(Optional<LootContextPredicate> player, Optional<LootContextPredicate> entity) implements AbstractCriterion.Conditions {
        public static final Codec<SummonMinionCriterion.Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(SummonMinionCriterion.Conditions::player),
                                EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("entity").forGetter(SummonMinionCriterion.Conditions::entity)
                        )
                        .apply(instance, SummonMinionCriterion.Conditions::new)
        );

        public static AdvancementCriterion<SummonMinionCriterion.Conditions> any() {
            return TerrarianCriteria.SUMMON_MINION.create(new SummonMinionCriterion.Conditions(Optional.empty(), Optional.empty()));
        }

        public static AdvancementCriterion<SummonMinionCriterion.Conditions> create(EntityPredicate.Builder entity) {
            return TerrarianCriteria.SUMMON_MINION
                    .create(new SummonMinionCriterion.Conditions(Optional.empty(), Optional.of(EntityPredicate.contextPredicateFromEntityPredicate(entity))));
        }

        public boolean matches(LootContext entity) {
            return this.entity.isEmpty() || this.entity.get().test(entity);
        }

        @Override
        public void validate(LootContextPredicateValidator validator) {
            AbstractCriterion.Conditions.super.validate(validator);
            validator.validateEntityPredicate(this.entity, ".entity");
        }
    }
}
