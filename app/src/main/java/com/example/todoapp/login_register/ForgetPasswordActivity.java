package com.example.todoapp.login_register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.todoapp.R;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        //setup rollback button
        ImageView rollaback = findViewById(R.id.rollback);
        rollaback.setOnClickListener(v -> {
            Intent intentRollBack = new Intent(this, LoginActivity.class);
            startActivity(intentRollBack);
        });

        //setup send request code button
        AppCompatButton sendREQUEST = (AppCompatButton) findViewById(R.id.request_btn);
        sendREQUEST.setOnClickListener(v -> {
            Intent intentRequest = new Intent(this, ResetPasswordActivity.class);
            startActivity(intentRequest);
        });
    }
}