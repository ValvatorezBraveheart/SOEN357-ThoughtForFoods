package com.example.thoughtforfoods.data;

import java.io.Serializable;

public class IngredientData implements Serializable {
    private String name;
    private IngredientCategory category;

    public IngredientData(String name, IngredientCategory category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    public void setCategory(IngredientCategory category) {
        this.category = category;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngredientData)) return false;
        IngredientData other = (IngredientData) o;
        return name.equals(other.name);
    }

}

