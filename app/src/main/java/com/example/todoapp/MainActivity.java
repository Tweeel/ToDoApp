package com.example.todoapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.todoapp.fragments.MenuFragment;
import com.example.todoapp.fragments.MyTaskFragment;
import com.example.todoapp.fragments.ProfileFragment;
import com.example.todoapp.fragments.QuickFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    //initialize the toolbar and here text
    private Toolbar toolbar;
    static TextView mTitle;
    Menu menu;
    Fragment fragment;
    MenuItem item;
    //variable to check if the data is received from the add Activity
    public static final int ADD_NOTE_REQUEST = 1;
    String id ="0";


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

        Intent intent = getIntent();
        if(intent.getStringExtra(AddTask.EXTRA_TITLE)!=null
                &&intent.getStringExtra(AddTask.EXTRA_DESCRIPTION)!=null
                &&intent.getStringExtra(AddTask.EXTRA_CATEGORY)!=null
                &&intent.getStringExtra(AddTask.EXTRA_ID)!=null){
            String title = intent.getStringExtra(AddTask.EXTRA_TITLE);
            String description = intent.getStringExtra(AddTask.EXTRA_DESCRIPTION);
            String category = intent.getStringExtra(AddTask.EXTRA_CATEGORY);
            String id = intent.getStringExtra(AddTask.EXTRA_ID);

            MyTaskFragment fragment = new MyTaskFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            Bundle datareceived = new Bundle();
            datareceived.putString("title", title);
            datareceived.putString("description", description);
            datareceived.putString("category", category);
            datareceived.putString("id",id);
            fragment.setArguments(datareceived);
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment).commit();
        }

        /*setup bottom navigation area*/
        //set the navigation view background color to transparent
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);

        //make the rounded corners for the BottomAppBar
        BottomAppBar bottomnav = findViewById(R.id.buttom);
        //make the bottom nav radius corners
        float radius = getResources().getDimension(R.dimen.default_corner_radius);
        MaterialShapeDrawable bottomBarBackground = (MaterialShapeDrawable) bottomnav.getBackground();
        bottomBarBackground.setShapeAppearanceModel(
                bottomBarBackground.getShapeAppearanceModel()
                        .toBuilder()
                        .setTopRightCorner(CornerFamily.ROUNDED, radius)
                        .setTopLeftCorner(CornerFamily.ROUNDED, radius)
                        .build());

        //setup fab
        FloatingActionButton addFAB = findViewById(R.id.add);
        addFAB.setOnClickListener(v -> {
            Intent intentadd = new Intent(this, AddTask.class);
            startActivity(intentadd);
        });

        //get the drawable
        Drawable myFabSrc = getResources().getDrawable(R.drawable.add);
        //set the color filter to the drawable
        myFabSrc.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        //set it to the fab button initialized before
        addFAB.setImageDrawable(myFabSrc);

        //set a default value for the fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, new MyTaskFragment()).commit();
        //display the selected fragment using our function
        bottomNavigationView.setOnItemSelectedListener(navListener);
    }

    //display the selected fragment
    @SuppressLint("NonConstantResourceId")
    private final BottomNavigationView.OnItemSelectedListener navListener =
            item -> {
                fragment = null;
                switch (item.getItemId()) {
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
                        .replace(R.id.fragmentContainerView, fragment).commit();
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