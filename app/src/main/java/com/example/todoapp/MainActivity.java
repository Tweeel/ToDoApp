package com.example.todoapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.todoapp.fragments.AddFragment;
import com.example.todoapp.fragments.MenuFragment;
import com.example.todoapp.fragments.MyTaskFragment;
import com.example.todoapp.fragments.ProfileFragment;
import com.example.todoapp.fragments.QuickFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    //initialize the toolbar and here text
    private Toolbar toolbar;
    TextView mTitle;
    Menu menu;
    Fragment fragment;
    MenuItem item;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup the toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

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
            mTitle.setText("New Task");

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
                fragment = null;
                switch (item.getItemId()){
                    case R.id.myTask:
                        fragment = new MyTaskFragment();
                        mTitle.setText("Work List");
                        break;
                    case R.id.menu:
                        fragment = new MenuFragment();
                        mTitle.setText("Work List");
                        break;
                    case R.id.Quick:
                        fragment = new QuickFragment();
                        mTitle.setText("Quick Notes");
                        break;
                    case R.id.profile:
                        fragment = new ProfileFragment();
                        mTitle.setText("Profile");
                        break;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView,fragment).commit();
                return true;
    };

    //initialize the menu of the toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflate = getMenuInflater();
        inflate.inflate(R.menu.toolbar_menu, menu);

        return true;
    }
}