package net.bluethedude.terrarianmight.datagen;

import net.bluethedude.terrarianmight.enchantments.TerrarianEnchantments;
import net.bluethedude.terrarianmight.util.TerrarianTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class TerrarianEnchantmentTagProvider extends FabricTagProvider.EnchantmentTagProvider {

    public TerrarianEnchantmentTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(TerrarianTags.Enchantments.MAGIC_EXCLUSIVE_SET)
                .add(TerrarianEnchantments.MANA_CAPACITY);
    }
}
