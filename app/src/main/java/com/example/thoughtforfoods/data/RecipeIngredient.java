package com.example.thoughtforfoods.data;

import java.io.Serializable;

public class RecipeIngredient implements Serializable {
    private IngredientData ingredientData;
    private String amountDescription;

    public RecipeIngredient(String amountDescription, IngredientData ingredientData) {
        this.amountDescription = amountDescription;
        this.ingredientData = ingredientData;
    }

    public IngredientData getIngredient() {
        return ingredientData;
    }

    public void setIngredient(IngredientData ingredientData) {
        this.ingredientData = ingredientData;
    }

    public String getAmountDescription() {
        return amountDescription;
    }

    public void setAmountDescription(String amountDescription) {
        this.amountDescription = amountDescription;
    }
}