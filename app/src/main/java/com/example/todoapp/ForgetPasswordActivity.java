package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        AppCompatButton sendREQUEST = (AppCompatButton) findViewById(R.id.request);
        sendREQUEST.setOnClickListener(v -> {
            Intent intentlogin = new Intent(this, ForgetPasswordActivity.class);
            startActivity(intentlogin);
        });
    }
}