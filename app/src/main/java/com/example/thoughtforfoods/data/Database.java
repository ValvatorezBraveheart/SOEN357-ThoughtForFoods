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

    private List<IngredientData> ingredientInPantry;

    private Database(Context context){
        allIngredients = new ArrayList<>();
        allRecipes = new ArrayList<>();
        ingredientInPantry = new ArrayList<>();
        loadIngredients(context);
        loadRecipes(context);
        ingredientInPantry = allIngredients;
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
                if (ingredientInPantry.contains(recipeIngredient.getIngredient())) {
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

    public void addIngredientToPantry(String ingredientName){
        for(IngredientData ingredientData : allIngredients){
            if (ingredientData.getName().equals(ingredientName)){
                ingredientInPantry.add(ingredientData);
                return;
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


}
