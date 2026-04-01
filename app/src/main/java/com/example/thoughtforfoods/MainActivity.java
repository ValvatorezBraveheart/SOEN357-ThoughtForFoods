package com.example.thoughtforfoods;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thoughtforfoods.data.Database;
import com.example.thoughtforfoods.data.Recipe;
import com.example.thoughtforfoods.data.RecipeResult;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

/**
 * MainActivity handles the logic for the primary screen.
 * It must extend AppCompatActivity to support older Android versions.
 */
public class MainActivity extends AppCompatActivity {
    SwitchMaterial onlyHaveAllIngredientSwitch;

    BottomNavigationView bottomNav;
    RecyclerView recipeRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Links this Java class to the XML layout (activity_main.xml)
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_nav);
        onlyHaveAllIngredientSwitch = findViewById(R.id.switch1);


        recipeRecyclerView = findViewById(R.id.recipesRecyclerView);
        recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<RecipeResult> recipeResults = Database.getInstance(this).getRecipeFromIngredients(false);
        RecipeResultAdapter adapter = new RecipeResultAdapter(recipeResults, recipe -> {
            Intent intent = new Intent(MainActivity.this, RecipePage.class);
            intent.putExtra("recipe", recipe.getRecipe());
            startActivity(intent);
            finish();
        });
        recipeRecyclerView.setAdapter(adapter);
        switchOnClick();

        bottomNav.setOnItemSelectedListener(item ->
                handleBottomNavSelection(item.getItemId())
        );

    }

    // Toggle on/off if recipe need to have all ingredient
    private void switchOnClick(){
        onlyHaveAllIngredientSwitch.setOnClickListener(view -> {
            List<RecipeResult> recipeResults = Database.getInstance(MainActivity.this).getRecipeFromIngredients(onlyHaveAllIngredientSwitch.isChecked());
            RecipeResultAdapter adapter = new RecipeResultAdapter(recipeResults, recipe -> {
                Intent intent = new Intent(MainActivity.this, RecipePage.class);
                intent.putExtra("recipe", recipe.getRecipe());
                startActivity(intent);
                finish();
            });
            recipeRecyclerView.setAdapter(adapter);
        });

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
