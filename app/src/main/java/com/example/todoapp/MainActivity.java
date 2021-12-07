package com.example.todoapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*setup bottom navigation area*/
        //set the navigation view background color to transparent
        bottomNavigationView =findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        //setup fab
        FloatingActionButton addFAB = findViewById(R.id.add);
        addFAB.setOnClickListener(v -> {
            Fragment fragment = new AddFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView,fragment).commit();
        });
        //get the drawable
        Drawable myFabSrc = getResources().getDrawable(R.drawable.add);
        //set the color filter to the drawable
        myFabSrc.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        //set it to the fab button initialized before
        addFAB.setImageDrawable(myFabSrc);

        //set a default value for the fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView,new MyTaskFragment()).commit();
        //display the selected fragment using our function
        bottomNavigationView.setOnItemSelectedListener(navListener);
    }

    //display the selected fragment
    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnItemSelectedListener navListener =
            item -> {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.myTask:
                        fragment = new MyTaskFragment();
                        break;
                    case R.id.menu:
                        fragment = new MenuFragment();
                        break;
                    case R.id.Quick:
                        fragment = new QuickFragment();
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView,fragment).commit();
                return true;                };
}