package com.example.thoughtforfoods;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.thoughtforfoods.data.Recipe;
import com.example.thoughtforfoods.data.RecipeIngredient;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RecipePage extends AppCompatActivity {

    BottomNavigationView bottomNav;
    Recipe currentRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Links this Java class to the XML layout (activity_main.xml)
        setContentView(R.layout.activity_recipe);

        bottomNav = findViewById(R.id.bottom_nav);


        bottomNav.setOnItemSelectedListener(item ->
                handleBottomNavSelection(item.getItemId())
        );
        currentRecipe = (Recipe) getIntent().getSerializableExtra("recipe");
        setUpData();
    }

    private void setUpData(){
        ImageView foodImage = findViewById(R.id.foodImage);
        TextView foodTitle = findViewById(R.id.foodTitle);
        TextView prepTime = findViewById(R.id.prepTime);
        TextView cookTime = findViewById(R.id.cookTime);
        TextView additionalTime = findViewById(R.id.additionalTime);
        TextView totalTime = findViewById(R.id.totalTime);
        TextView servings = findViewById(R.id.servings);
        TextView nbrCalories = findViewById(R.id.nbrCalories);
        TextView nbrFat = findViewById(R.id.nbrFat);
        TextView nbrCarbs = findViewById(R.id.nbrCarbs);
        TextView nbrProtein = findViewById(R.id.nbrProtein);
        TextView tvIngredients = findViewById(R.id.tvIngredients);
        TextView tvInstructions = findViewById(R.id.tvInstructions);


        Glide.with(this)
                .load(currentRecipe.getRecipeImgUrl())
                .into(foodImage);
        foodTitle.setText(currentRecipe.getRecipeName());
        prepTime.setText(currentRecipe.getDetails().getPrepTime());
        cookTime.setText(currentRecipe.getDetails().getCookTime());
        additionalTime.setText(currentRecipe.getDetails().getWaitTime());
        totalTime.setText(currentRecipe.getDetails().getTotalTime());
        servings.setText(currentRecipe.getDetails().getServings());

        nbrCalories.setText(String.valueOf(currentRecipe.getNutrition().getCalories())+" kcal");
        nbrFat.setText(String.valueOf(currentRecipe.getNutrition().getFat())+" g");
        nbrCarbs.setText(String.valueOf(currentRecipe.getNutrition().getCarbs())+" g");
        nbrProtein.setText(String.valueOf(currentRecipe.getNutrition().getProtein())+" g");

        StringBuilder ingredientStr = new StringBuilder();
        for (RecipeIngredient recipeIngredient : currentRecipe.getIngredientList()){
            ingredientStr.append("- ");
            ingredientStr.append(recipeIngredient.getAmountDescription());
            ingredientStr.append(" ");
            ingredientStr.append(recipeIngredient.getIngredient().getName());
            ingredientStr.append("\n");
        }
        tvIngredients.setText(ingredientStr);

        StringBuilder instructionStr = new StringBuilder();
        for (String instruction : currentRecipe.getInstructions()){
            instructionStr.append(instruction);
            instructionStr.append("\n");
        }
        tvInstructions.setText(instructionStr);
    }

    private boolean handleBottomNavSelection(int id) {
        if (id == R.id.find_recipe) {
            startActivity(new Intent(this, MainActivity.class));
            return true;
        }

        if (id == R.id.pantry) {
            startActivity(new Intent(this, PantryPage.class));
            return true;
        }

        if (id == R.id.nav_settings) {
            startActivity(new Intent(this, SettingsPage.class));
            return true;
        }

        return false;
    }
}
