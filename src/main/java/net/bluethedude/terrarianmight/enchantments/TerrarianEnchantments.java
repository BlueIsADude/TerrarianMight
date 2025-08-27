package net.bluethedude.terrarianmight.enchantments;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.util.TerrarianTags;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class TerrarianEnchantments {
    public static final RegistryKey<Enchantment> MANA_CAPACITY =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(TerrarianMight.MOD_ID, "mana_capacity"));
    public static final RegistryKey<Enchantment> ABSORBING =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(TerrarianMight.MOD_ID, "absorbing"));
    public static final RegistryKey<Enchantment> ARCANE_GROWTH =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(TerrarianMight.MOD_ID, "arcane_growth"));

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, MANA_CAPACITY, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(TerrarianTags.Items.MAGIC_ENCHANTABLE),
                        5,
                        3,
                        Enchantment.leveledCost(5, 7),
                        Enchantment.leveledCost(25, 9),
                        2,
                        AttributeModifierSlot.HAND))
                .exclusiveSet(enchantments.getOrThrow(TerrarianTags.Enchantments.MAGIC_EXCLUSIVE_SET))
        );
    }


    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
