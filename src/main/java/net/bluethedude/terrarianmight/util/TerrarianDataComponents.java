package net.bluethedude.terrarianmight.util;

import com.mojang.serialization.Codec;
import net.bluethedude.terrarianmight.TerrarianMight;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class TerrarianDataComponents {
    public static final ComponentType<Integer> MANA =
            register("mana", builder -> builder.codec(Codec.INT));

    private static <T> ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(TerrarianMight.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes() {
        TerrarianMight.LOGGER.info("Registering Data Component Types for " + TerrarianMight.MOD_ID);
    }
}
