package net.bluethedude.terrarianmight.datagen;

import net.bluethedude.terrarianmight.block.TerrarianBlocks;
import net.bluethedude.terrarianmight.item.TerrarianItems;
import net.bluethedude.terrarianmight.util.TerrarianTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class TerrarianRecipeProvider extends FabricRecipeProvider {
    public TerrarianRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerPlanksRecipe(exporter, TerrarianBlocks.YELLOW_WILLOW_PLANKS, TerrarianTags.Items.YELLOW_WILLOW_LOGS, 4);
        createStairsRecipe(TerrarianBlocks.YELLOW_WILLOW_STAIRS, Ingredient.ofItems(TerrarianBlocks.YELLOW_WILLOW_PLANKS));
        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, TerrarianBlocks.YELLOW_WILLOW_SLAB, Ingredient.ofItems(TerrarianBlocks.YELLOW_WILLOW_PLANKS));
        createFenceRecipe(TerrarianBlocks.YELLOW_WILLOW_FENCE, Ingredient.ofItems(TerrarianBlocks.YELLOW_WILLOW_PLANKS));
        createFenceGateRecipe(TerrarianBlocks.YELLOW_WILLOW_FENCE_GATE, Ingredient.ofItems(TerrarianBlocks.YELLOW_WILLOW_PLANKS));
        createTransmutationRecipe(TerrarianBlocks.YELLOW_WILLOW_BUTTON, Ingredient.ofItems(TerrarianBlocks.YELLOW_WILLOW_PLANKS));
        createPressurePlateRecipe(RecipeCategory.REDSTONE, TerrarianBlocks.YELLOW_WILLOW_PRESSURE_PLATE, Ingredient.ofItems(TerrarianBlocks.YELLOW_WILLOW_PLANKS));
        createDoorRecipe(TerrarianBlocks.YELLOW_WILLOW_DOOR, Ingredient.ofItems(TerrarianBlocks.YELLOW_WILLOW_PLANKS));
        createTrapdoorRecipe(TerrarianBlocks.YELLOW_WILLOW_TRAPDOOR, Ingredient.ofItems(TerrarianBlocks.YELLOW_WILLOW_PLANKS));
        createSignRecipe(TerrarianItems.YELLOW_WILLOW_SIGN_ITEM, Ingredient.ofItems(TerrarianBlocks.YELLOW_WILLOW_PLANKS));
        offerHangingSignRecipe(exporter, TerrarianItems.YELLOW_WILLOW_HANGING_SIGN_ITEM, TerrarianBlocks.STRIPPED_YELLOW_WILLOW_LOG);

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
    }
}