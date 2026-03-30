package com.example.thoughtforfoods.data;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    private String recipeImgUrl;
    private String recipeName;
    private RecipeDetails details;
    private List<RecipeIngredient> ingredientList;
    private RecipeNutrition nutrition;
    private List<String> instructions;
    public Recipe(String recipeImgUrl, String recipeName, RecipeDetails details, List<RecipeIngredient> ingredientList, RecipeNutrition nutrition, List<String> instructions) {
        this.recipeImgUrl = recipeImgUrl;
        this.recipeName = recipeName;
        this.details = details;
        this.ingredientList = ingredientList;
        this.nutrition = nutrition;
        this.instructions = instructions;
    }

    public String getRecipeImgUrl() {
        return recipeImgUrl;
    }

    public void setRecipeImgUrl(String recipeImgUrl) {
        this.recipeImgUrl = recipeImgUrl;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public RecipeDetails getDetails() {
        return details;
    }

    public void setDetails(RecipeDetails details) {
        this.details = details;
    }

    public List<RecipeIngredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<RecipeIngredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public RecipeNutrition getNutrition() {
        return nutrition;
    }

    public void setNutrition(RecipeNutrition nutrition) {
        this.nutrition = nutrition;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }
}
