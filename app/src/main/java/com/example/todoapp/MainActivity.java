package com.example.todoapp;

import static com.google.android.material.shape.CornerFamily.ROUNDED;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.todoapp.fragments.MonthFragment;
import com.example.todoapp.fragments.MyTaskFragment;
import com.example.todoapp.fragments.NotesFragment;
import com.example.todoapp.fragments.ListsFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

        /* receive the data from the add task activity and send it to the fragment
        * */
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
                        .setTopRightCorner(ROUNDED, radius)
                        .setTopLeftCorner(ROUNDED, radius)
                        .build());
        /*setup fab*/
        FloatingActionButton addFAB = findViewById(R.id.add);
        /*Create the Dialog here*/
        Dialog dialog_new = new Dialog(this);
        dialog_new.setContentView(R.layout.create_new);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog_new.getWindow().setBackgroundDrawable(getDrawable(R.drawable.back_round_white));
        }
        dialog_new.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Window window_new = dialog_new.getWindow();
        window_new.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window_new.setGravity(Gravity.CENTER);

        /*Create the Dialog for the new list*/
        Dialog dialog_list = new Dialog(this);
        dialog_list.setContentView(R.layout.new_note);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog_list.getWindow().setBackgroundDrawable(getDrawable(R.drawable.back_round_white));
        }
        dialog_list.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        Window window_list = dialog_list.getWindow();
        window_list.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window_list.setGravity(Gravity.CENTER);

        //setup the dialog new buttons
        TextView task = dialog_new.findViewById(R.id.task);
        TextView note = dialog_new.findViewById(R.id.note);
        TextView list = dialog_new.findViewById(R.id.list);

        task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentadd = new Intent(MainActivity.this, AddTask.class);
                startActivity(intentadd);
                dialog_new.dismiss();
            }
        });

        note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "note", Toast.LENGTH_SHORT).show();
                dialog_new.dismiss();
            }
        });

        list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_list.show(); // Showing the dialog_new here
                dialog_new.dismiss();
            }
        });

        /*onclick to fab to show the dialog_new*/
        addFAB.setOnClickListener(v -> {
            dialog_new.show(); // Showing the dialog_new here
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
                        mTitle.setText("My Tasks");
                        break;
                    case R.id.calender:
                        fragment = new MonthFragment();
                        mTitle.setText("Month");
                        break;
                    case R.id.menu:
                        fragment = new ListsFragment();
                        mTitle.setText("Lists");
                        break;
                    case R.id.Quick:
                        fragment = new NotesFragment();
                        mTitle.setText("Notes");
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