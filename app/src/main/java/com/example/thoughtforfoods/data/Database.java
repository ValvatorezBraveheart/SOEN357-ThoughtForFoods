package com.example.thoughtforfoods.data;

import android.content.Context;

import com.example.thoughtforfoods.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

// Mocking of a database containing all the ingredients and recipes
// Singleton to persist across activity
public class Database {
    private static Database instance;
    private List<IngredientData> allIngredients;
    private List<Recipe> allRecipes;

    private List<PantrySectionData> pantrySectionDataList;

    private Database(Context context){
        allIngredients = new ArrayList<>();
        allRecipes = new ArrayList<>();
        pantrySectionDataList = new ArrayList<>();
        for (IngredientCategory category:IngredientCategory.values()){
            pantrySectionDataList.add(new PantrySectionData(category, new ArrayList<>()));
        }
        loadIngredients(context);
        loadRecipes(context);
        addIngredientToPantry("Egg");
    }
    public static Database getInstance(Context context){
        if (instance == null){
            instance = new Database(context.getApplicationContext());
        }
        return instance;
    }

    public List<RecipeResult> getRecipeFromIngredients(boolean matchAll){
        List<RecipeResult> results = new ArrayList<>();
        for (Recipe recipe:allRecipes){
            int totalIngredient = recipe.getIngredientList().size();
            int matchedIngredient = 0;
            for (RecipeIngredient recipeIngredient:recipe.getIngredientList()){
                for(PantrySectionData pantrySection:pantrySectionDataList){
                    if (pantrySection.getIngredientInPantry().contains(recipeIngredient.getIngredient())) {
                        matchedIngredient++;
                    }
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

    public List<IngredientData> getAllIngredients() {
        return allIngredients;
    }

    public void addIngredientToPantry(String ingredientName){
        for(IngredientData ingredientData : allIngredients){
            if (!ingredientData.getName().equalsIgnoreCase(ingredientName)) {
                continue;
            }
            for (PantrySectionData section : pantrySectionDataList) {
                if (section.getCategory() == ingredientData.getCategory()) {
                    section.getIngredientInPantry().add(ingredientData);
                    return;
                }
            }
        }
    }
    public void removeIngredientFromPantry(String ingredientName) {
        for (IngredientData ingredientData : allIngredients) {
            if (!ingredientData.getName().equalsIgnoreCase(ingredientName)) {
                continue;
            }

            for (PantrySectionData section : pantrySectionDataList) {
                if (section.getCategory() == ingredientData.getCategory()) {
                    // Remove ingredient from this section
                    section.getIngredientInPantry().remove(ingredientData);
                    return; // stop once removed
                }
            }
        }
    }

    // For the delete all button
    public void removeAllIngredientsFromPantry(){
        for (IngredientData ingredientData : allIngredients) {
            for (PantrySectionData section : pantrySectionDataList) {
                section.setIngredientInPantry(new ArrayList<>());
            }
        }
    }

    private void loadIngredients(Context context){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<IngredientData>>() {}.getType();

        InputStream is = context.getResources().openRawResource(R.raw.ingredients);
        InputStreamReader reader = new InputStreamReader(is);
        allIngredients.addAll(gson.fromJson(reader, listType));

    }

    private void loadRecipes(Context context){
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Recipe>>() {}.getType();

        InputStream is = context.getResources().openRawResource(R.raw.recipes);
        InputStreamReader reader = new InputStreamReader(is);
        allRecipes.addAll(gson.fromJson(reader, listType));
    }


    public List<PantrySectionData> getPantrySectionDataList() {
        return pantrySectionDataList;
    }

}
