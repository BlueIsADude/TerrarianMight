package net.bluethedude.terrarianmight.datagen;

import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.bluethedude.terrarianmight.util.TerrarianTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.resource.featuretoggle.FeatureSet;

import java.util.concurrent.CompletableFuture;

import static net.minecraft.data.family.BlockFamilies.register;

public class TerrarianRecipeProvider extends FabricRecipeProvider {
    public TerrarianRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        BlockFamily yellowWillowFamily = register(TerrarianBlocks.YELLOW_WILLOW_PLANKS)
                .button(TerrarianBlocks.YELLOW_WILLOW_BUTTON)
                .fence(TerrarianBlocks.YELLOW_WILLOW_FENCE)
                .fenceGate(TerrarianBlocks.YELLOW_WILLOW_FENCE_GATE)
                .pressurePlate(TerrarianBlocks.YELLOW_WILLOW_PRESSURE_PLATE)
                .sign(TerrarianBlocks.YELLOW_WILLOW_SIGN, TerrarianBlocks.YELLOW_WILLOW_WALL_SIGN)
                .slab(TerrarianBlocks.YELLOW_WILLOW_SLAB)
                .stairs(TerrarianBlocks.YELLOW_WILLOW_STAIRS)
                .door(TerrarianBlocks.YELLOW_WILLOW_DOOR)
                .trapdoor(TerrarianBlocks.YELLOW_WILLOW_TRAPDOOR)
                .group("wooden")
                .unlockCriterionName("has_planks")
                .build();
        generateFamily(exporter, yellowWillowFamily, FeatureSet.of(FeatureFlags.VANILLA));
        offerPlanksRecipe(exporter, TerrarianBlocks.YELLOW_WILLOW_PLANKS, TerrarianTags.Items.YELLOW_WILLOW_LOGS, 4);

        offerHangingSignRecipe(exporter, TerrarianItems.YELLOW_WILLOW_HANGING_SIGN_ITEM, TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG);
        offerBoatRecipe(exporter, TerrarianItems.YELLOW_WILLOW_BOAT, TerrarianBlocks.YELLOW_WILLOW_PLANKS);
        offerChestBoatRecipe(exporter, TerrarianItems.YELLOW_WILLOW_CHEST_BOAT, TerrarianItems.YELLOW_WILLOW_BOAT);

        offer2x2CompactingRecipe(exporter, RecipeCategory.COMBAT, TerrarianItems.LIFE_CRYSTAL, TerrarianItems.LIFE_SHARD);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, TerrarianItems.LIFE_FRUIT)
                .input(TerrarianItems.LIFE_CRYSTAL)
                .input(TerrarianItems.LIFE_CRYSTAL)
                .input(Items.GLOW_BERRIES)
                .input(Items.GLOW_BERRIES)
                .criterion("has_life_crystal", conditionsFromItem(TerrarianItems.LIFE_CRYSTAL))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.DECORATIONS, TerrarianBlocks.HEART_LANTERN)
                .input('#', Items.LANTERN)
                .input('X', TerrarianItems.LIFE_SHARD)
                .pattern(" X ")
                .pattern("X#X")
                .pattern(" X ")
                .criterion("has_life_shard", conditionsFromItem(TerrarianItems.LIFE_SHARD))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, TerrarianItems.AMETHYST_STAFF)
                .input('#', Items.AMETHYST_SHARD)
                .input('X', Items.COPPER_INGOT)
                .input('O', Items.LAPIS_LAZULI)
                .pattern(" #O")
                .pattern(" X#")
                .pattern("X  ")
                .criterion("has_amethyst_shard", conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter);
    }
}