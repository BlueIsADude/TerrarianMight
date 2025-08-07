package net.bluethedude.terrarianmight.item;

import net.bluethedude.terrarianmight.TerrarianConfig;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class TerrarianFoodComponents {
    public static final FoodComponent LIFE_FRUIT = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1f).alwaysEdible()
            .statusEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, TerrarianConfig.fruitDuration, TerrarianConfig.fruitAmplifier), 1.0F).build();

}
