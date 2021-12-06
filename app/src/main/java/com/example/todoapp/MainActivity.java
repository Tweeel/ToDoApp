package com.example.todoapp;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*setup bottom navigation area*/
        //set the navigation view background color to transparent
        bottomNavigationView =findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        //initialize fab
        FloatingActionButton addFAB = findViewById(R.id.add);
        //get the drawable
        Drawable myFabSrc = getResources().getDrawable(R.drawable.add);
        //set the color filter to the drawable
        myFabSrc.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        //set it to the fab button initialized before
        addFAB.setImageDrawable(myFabSrc);

    }
}