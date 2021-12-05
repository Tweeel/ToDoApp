package com.example.todoapp.login_regester;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.todoapp.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //setup register button
        AppCompatButton registerButton = findViewById(R.id.register);
        registerButton.setOnClickListener(v -> {
            Intent intentlogin = new Intent(RegistrationActivity.this, SuccesfulActivity.class);
            startActivity(intentlogin);
        });

        //setup login setup
        TextView login = findViewById(R.id.login);
        login.setOnClickListener(v -> {
            Intent intentlogin = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intentlogin);
        });
    }
}