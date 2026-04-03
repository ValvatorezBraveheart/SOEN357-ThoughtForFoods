package com.example.thoughtforfoods.data;


import java.io.Serializable;

public class RecipeResult implements Serializable {
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