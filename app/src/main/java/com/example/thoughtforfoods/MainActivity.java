package com.example.thoughtforfoods;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity handles the logic for the primary screen.
 * It must extend AppCompatActivity to support older Android versions.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Links this Java class to the XML layout (activity_main.xml)
        setContentView(R.layout.activity_main);


    }
}
