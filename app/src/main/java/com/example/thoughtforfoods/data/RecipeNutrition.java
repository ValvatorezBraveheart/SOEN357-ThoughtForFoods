package com.example.thoughtforfoods.data;

import java.io.Serializable;

public class RecipeNutrition implements Serializable {
    private int calories;
    private int fat;
    private int carbs;
    private int protein;

    public RecipeNutrition(int calories, int fat, int carbs, int protein) {
        this.calories = calories;
        this.fat = fat;
        this.carbs = carbs;
        this.protein = protein;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }
}