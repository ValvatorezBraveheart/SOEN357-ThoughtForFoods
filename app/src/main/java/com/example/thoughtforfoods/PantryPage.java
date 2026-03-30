package com.example.thoughtforfoods;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PantryPage extends AppCompatActivity {

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Links this Java class to the XML layout (activity_main.xml)
        setContentView(R.layout.activity_pantry);

        bottomNav = findViewById(R.id.bottom_nav);

        bottomNav.setOnItemSelectedListener(item ->
                handleBottomNavSelection(item.getItemId())
        );


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
