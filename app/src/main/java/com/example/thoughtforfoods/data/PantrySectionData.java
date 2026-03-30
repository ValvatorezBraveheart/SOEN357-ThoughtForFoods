package com.example.thoughtforfoods.data;

import java.util.List;

public class PantrySectionData {
    private IngredientCategory category;
    private List<IngredientData> ingredientInPantry;

    public PantrySectionData(IngredientCategory category, List<IngredientData> ingredientInPantry) {
        this.category = category;
        this.ingredientInPantry = ingredientInPantry;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    public void setCategory(IngredientCategory category) {
        this.category = category;
    }

    public List<IngredientData> getIngredientInPantry() {
        return ingredientInPantry;
    }

    public void setIngredientInPantry(List<IngredientData> ingredientInPantry) {
        this.ingredientInPantry = ingredientInPantry;
    }
}
