package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //setup rollback button
        ImageView rollaback = findViewById(R.id.rollback);
        rollaback.setOnClickListener(v -> {
            Intent intentlogin = new Intent(this, IntroActivity.class);
            startActivity(intentlogin);
        });

        //setup forget password
        TextView forget_password_button = findViewById(R.id.forget);
        forget_password_button.setOnClickListener(v -> {
            Intent intentlogin = new Intent(this, ForgetPasswordActivity.class);
            startActivity(intentlogin);
        });

    }
}