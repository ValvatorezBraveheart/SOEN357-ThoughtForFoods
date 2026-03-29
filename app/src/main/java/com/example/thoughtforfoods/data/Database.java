package com.example.thoughtforfoods.data;

import java.util.ArrayList;
import java.util.List;

// Mocking of a database containing all the ingredients and recipes
public class Database {
    private List<IngredientData> allIngredients;
    private List<Recipe> allRecipes;

    private List<IngredientData> ingredientInPantry;

    public List<RecipeResult> getRecipeFromIngredients(List<IngredientData> ingredients, boolean matchAll){
        List<RecipeResult> results = new ArrayList<>();
        for (Recipe recipe:allRecipes){
            int totalIngredient = recipe.getIngredientList().size();
            int matchedIngredient = 0;
            for (RecipeIngredient recipeIngredient:recipe.getIngredientList()){
                if (ingredients.contains(recipeIngredient.getIngredient())) {
                    matchedIngredient++;
                }
            }
            int missingIngredientCount = totalIngredient - matchedIngredient;
            if (!matchAll){
                if (missingIngredientCount<=2){
                    results.add(new RecipeResult(recipe, missingIngredientCount));
                }
            } else {
                if (missingIngredientCount==0){
                    results.add(new RecipeResult(recipe, 0));
                }
            }
        }
        return results;
    }

    public class RecipeResult {
        private Recipe recipe;
        private int missingIngredientCount;

        public RecipeResult(Recipe recipe, int missingIngredientCount) {
            this.recipe = recipe;
            this.missingIngredientCount = missingIngredientCount;
        }

        public Recipe getRecipe() {
            return recipe;
        }

        public void setRecipe(Recipe recipe) {
            this.recipe = recipe;
        }

        public int getMissingIngredientCount() {
            return missingIngredientCount;
        }

        public void setMissingIngredientCounts(int missingIngredientCounts) {
            this.missingIngredientCount = missingIngredientCounts;
        }
    }


}
