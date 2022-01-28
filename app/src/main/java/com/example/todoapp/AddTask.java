package com.example.todoapp;

import static android.text.TextUtils.isEmpty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.todoapp.fragments.MyTaskFragment;

public class AddTask extends AppCompatActivity {

    private EditText TitleView, descriptionView, categoryView;
    private Button button;
    private TextView titre, dateView, timeView;
    String id;

    public static final String EXTRA_TITLE = "com.example.todoapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.todoapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_CATEGORY = "com.example.todoapp.EXTRA_CATEGORY";
    public static final String EXTRA_TIME = "com.example.todoapp.EXTRA_TIME";
    public static final String EXTRA_DATE = "com.example.todoapp.EXTRA_DATE";
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
        dateView = findViewById(R.id.datePicker);
        timeView = findViewById(R.id.timeStart);

        dateView.setOnClickListener(view -> {
            DialogFragment newFragment = new DatePickerFragment();
            newFragment.show(getSupportFragmentManager(),"datePicker");
        });

        timeView.setOnClickListener(view -> {

            DialogFragment newFragment = new TimePickerFragment();
            newFragment.show(getSupportFragmentManager(), "datePicker");
        });

        Intent intent = getIntent();
        if(!(isEmpty(intent.getStringExtra(MyTaskFragment.EXTRA_TITLE))
                ||isEmpty(intent.getStringExtra(MyTaskFragment.EXTRA_DESCRIPTION))
                ||isEmpty(intent.getStringExtra(MyTaskFragment.EXTRA_CATEGORY))
                ||isEmpty(intent.getStringExtra(MyTaskFragment.EXTRA_DATE))
                ||isEmpty(intent.getStringExtra(MyTaskFragment.EXTRA_TIME))
                ||isEmpty(intent.getStringExtra(MyTaskFragment.EXTRA_ID)))){
            String title = intent.getStringExtra(MyTaskFragment.EXTRA_TITLE);
            String description = intent.getStringExtra(MyTaskFragment.EXTRA_DESCRIPTION);
            String category = intent.getStringExtra(MyTaskFragment.EXTRA_CATEGORY);
            String date = intent.getStringExtra(MyTaskFragment.EXTRA_DATE);
            String time = intent.getStringExtra(MyTaskFragment.EXTRA_TIME);
            id = intent.getStringExtra(MyTaskFragment.EXTRA_ID);

            TitleView.setText(title);
            descriptionView.setText(description);
            dateView.setText(date);
            timeView.setText(time);
            categoryView.setText(category);
            titre.setText("Edit Task");
            button.setText("Edit");
        }else {
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
        String date = dateView.getText().toString();
        String time = timeView.getText().toString();

        //trim function is to remove the spaces from both ends of a string.
        if(title.trim().isEmpty()){
            Toast.makeText(this,"Please insert a title and description",Toast.LENGTH_SHORT).show();
        }else {
            Intent data = new Intent(this, MainActivity.class);
            data.putExtra(EXTRA_TITLE, title);
            data.putExtra(EXTRA_DESCRIPTION, description);
            data.putExtra(EXTRA_CATEGORY, category);
            data.putExtra(EXTRA_TIME, time);
            data.putExtra(EXTRA_DATE, date);
            data.putExtra(EXTRA_ID, id);
            startActivity(data);
            finish();
        }
    }

    /*function to process the date picked*/
    public void processDatePickerResult(int year, int month, int day) {
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (day_string +
                "/" + month_string + "/" + year_string);
        dateView.setText(dateMessage);
    }

    /*function to process the time picked*/
    public void processTimePickerResult(int hourOfDay, int minutes) {
        String minutes_string = Integer.toString(minutes);
        String timeSet="";
        if (hourOfDay > 12) {
            hourOfDay -= 12;
            timeSet = "PM";
        } else if (hourOfDay == 0) {
            hourOfDay += 12;
            timeSet = "AM";
        } else if (hourOfDay == 12){
            timeSet = "PM";
        }else{
            timeSet = "AM";
        }
        String hourOfDay_string = Integer.toString(hourOfDay);

        String timeMessage = (hourOfDay_string +" : " + minutes_string + " "+ timeSet);

        timeView.setText(timeMessage);
    }
}