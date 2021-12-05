package com.example.todoapp.login_regester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.todoapp.R;
import com.example.todoapp.intro.IntroActivity;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //setup rollback button
        ImageView rollaback = findViewById(R.id.rollback);
        rollaback.setOnClickListener(v -> {
            Intent intentRollBack = new Intent(this, IntroActivity.class);
            startActivity(intentRollBack);
        });

        //setup forget password
        TextView forget_password_button = findViewById(R.id.forget);
        forget_password_button.setOnClickListener(v -> {
            Intent intentlogin = new Intent(this, ForgetPasswordActivity.class);
            startActivity(intentlogin);
        });

        //setup signup setup
        TextView register = findViewById(R.id.register);
        register.setOnClickListener(v -> {
            Intent intentlogin = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(intentlogin);
        });

    }
}