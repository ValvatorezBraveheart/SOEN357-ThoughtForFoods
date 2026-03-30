package com.example.thoughtforfoods;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thoughtforfoods.data.Database;
import com.example.thoughtforfoods.data.IngredientData;
import com.example.thoughtforfoods.data.PantrySectionData;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class PantryPage extends AppCompatActivity {

    BottomNavigationView bottomNav;
    private PantryAdapter pantryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Links this Java class to the XML layout (activity_main.xml)
        setContentView(R.layout.activity_pantry);

        bottomNav = findViewById(R.id.bottom_nav);

        bottomNav.setOnItemSelectedListener(item ->
                handleBottomNavSelection(item.getItemId())
        );
        setUpPantry();
        setUpAddRemove();
    }

    private void setUpPantry(){
        RecyclerView recyclerView = findViewById(R.id.categoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Database db = Database.getInstance(this);
        List<PantrySectionData> sections = db.getPantrySectionDataList();
        pantryAdapter = new PantryAdapter(sections);
        recyclerView.setAdapter(pantryAdapter);
    }

    private void setUpAddRemove(){
        AutoCompleteTextView searchView = findViewById(R.id.searchView);
        List<IngredientData> allIngredients = Database.getInstance(this).getAllIngredients();
        List<String> allIngredientNames = new ArrayList<>();
        for (IngredientData ingredientData: allIngredients){
            allIngredientNames.add(ingredientData.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                allIngredientNames
        );
        searchView.setAdapter(adapter);
        searchView.setThreshold(1);

        // When click on the dropdown
        searchView.setOnItemClickListener((parent, view, position, id) -> {
            String selected = (String) parent.getItemAtPosition(position);
            Database.getInstance(this).addIngredientToPantry(selected);
            pantryAdapter.notifyDataSetChanged();
            searchView.setText("");
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
            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

}
