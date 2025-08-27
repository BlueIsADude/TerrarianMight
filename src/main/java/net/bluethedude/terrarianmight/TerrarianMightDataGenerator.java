package net.bluethedude.terrarianmight;

import net.bluethedude.terrarianmight.datagen.*;
import net.bluethedude.terrarianmight.enchantments.TerrarianEnchantments;
import net.bluethedude.terrarianmight.trim.TerrarianTrimMaterials;
import net.bluethedude.terrarianmight.world.TerrarianConfiguredFeatures;
import net.bluethedude.terrarianmight.world.TerrarianPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class TerrarianMightDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(TerrarianBlockTagProvider::new);
        pack.addProvider(TerrarianEnchantmentTagProvider::new);
        pack.addProvider(TerrarianItemTagProvider::new);
        pack.addProvider(TerrarianLootTableProvider::new);
        pack.addProvider(TerrarianModelProvider::new);
        pack.addProvider(TerrarianRecipeProvider::new);
        pack.addProvider(TerrarianRegistryDataGenerator::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.TRIM_MATERIAL, TerrarianTrimMaterials::bootstrap);

        registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, TerrarianEnchantments::bootstrap);

        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, TerrarianConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, TerrarianPlacedFeatures::bootstrap);
    }
}
