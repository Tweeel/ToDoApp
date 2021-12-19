package com.example.todoapp;

import static android.text.TextUtils.isEmpty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.fragments.TodayFragment;

public class AddTask extends AppCompatActivity {

    private EditText TitleView, descriptionView, categoryView;
    private Button button;
    private TextView titre,header;
    String id;

    public static final String EXTRA_TITLE = "com.example.todoapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.todoapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_CATEGORY = "com.example.todoapp.EXTRA_CATEGORY";
    public static final String EXTRA_ID = "com.example.todoapp.EXTRA_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setup the roll back button
        setContentView(R.layout.activity_add_task);
        ImageView rollback = findViewById(R.id.rollback);
        rollback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentadd = new Intent(AddTask.this, MainActivity.class);
                startActivity(intentadd);
            }
        });
        //set the id to 0 by default if we are in the case of adding a task
        id = "0";
        TitleView = findViewById(R.id.editTextTitle);
        descriptionView = findViewById(R.id.editTexDescription);
        categoryView = findViewById(R.id.editTextCategory);
        button = findViewById(R.id.add);
        titre = findViewById(R.id.title);
        header = findViewById(R.id.header);

        Intent intent = getIntent();
        if(!(isEmpty(intent.getStringExtra(TodayFragment.EXTRA_TITLE))
                ||isEmpty(intent.getStringExtra(TodayFragment.EXTRA_DESCRIPTION))
                ||isEmpty(intent.getStringExtra(TodayFragment.EXTRA_CATEGORY))
                ||isEmpty(intent.getStringExtra(TodayFragment.EXTRA_ID)))){
            String title = intent.getStringExtra(TodayFragment.EXTRA_TITLE);
            String description = intent.getStringExtra(TodayFragment.EXTRA_DESCRIPTION);
            String category = intent.getStringExtra(TodayFragment.EXTRA_CATEGORY);
            id = intent.getStringExtra(TodayFragment.EXTRA_ID);
            Log.d("test", "id in add task / id received  = "+ id);
            TitleView.setText(title);
            descriptionView.setText(description);
            categoryView.setText(category);
            titre.setText("Edit Task");
            header.setText("Edit your Task");
            button.setText("Edit");
        }else {
            Log.d("test", "we are in add new task");
            id = "0";
        }

        //call the function when click the button
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    //function to sane tasks
    private void saveTask() {
        String title = TitleView.getText().toString();
        String description = descriptionView.getText().toString();
        String category = categoryView.getText().toString();

        //trim function is to remove the spaces from both ends of a string.
        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this,"Please insert a title and description",Toast.LENGTH_SHORT).show();
        }else {
            Intent data = new Intent(this, MainActivity.class);
            data.putExtra(EXTRA_TITLE, title);
            data.putExtra(EXTRA_DESCRIPTION, description);
            data.putExtra(EXTRA_CATEGORY, category);
            data.putExtra(EXTRA_ID, id);
            Log.d("test", title);
            Log.d("test", description);
            Log.d("test", category);
            Log.d("test", id);
            startActivity(data);
            finish();
        }
    }
}