package net.bluethedude.terrarianmight.trim;

import net.bluethedude.terrarianmight.TerrarianMight;
import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.minecraft.item.Item;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class TerrarianTrimMaterials {
    public static final RegistryKey<ArmorTrimMaterial> LIFE_SHARD = RegistryKey.of(RegistryKeys.TRIM_MATERIAL,
            Identifier.of(TerrarianMight.MOD_ID, "life_shard")
    );

    public static void bootstrap(Registerable<ArmorTrimMaterial> registerable) {
        register(registerable, LIFE_SHARD, Registries.ITEM.getEntry(TerrarianItems.LIFE_SHARD),
                Style.EMPTY.withColor(TextColor.parse("#ba255c").getOrThrow()), 0.4f);

    }

    private static void register(Registerable<ArmorTrimMaterial> registerable, RegistryKey<ArmorTrimMaterial> armorTrimKey, RegistryEntry<Item> item,
                                 Style style, float itemModelIndex) {
        ArmorTrimMaterial trimMaterial = new ArmorTrimMaterial(armorTrimKey.getValue().getPath(), item, itemModelIndex, Map.of(),
                Text.translatable(Util.createTranslationKey("trim_material", armorTrimKey.getValue())).fillStyle(style));

        registerable.register(armorTrimKey, trimMaterial);
    }
}
