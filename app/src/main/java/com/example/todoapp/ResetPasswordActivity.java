package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        //setup rollback button
        ImageView rollaback = findViewById(R.id.rollback);
        rollaback.setOnClickListener(v -> {
            Intent intentRollBack = new Intent(this, ForgetPasswordActivity.class);
            startActivity(intentRollBack);
        });

        //setup send change password button
        AppCompatButton change_btn = (AppCompatButton) findViewById(R.id.change_btn);
        change_btn.setOnClickListener(v -> {
            Intent intentRequest = new Intent(this, SuccesfulActivity.class);
            startActivity(intentRequest);
        });
    }
}