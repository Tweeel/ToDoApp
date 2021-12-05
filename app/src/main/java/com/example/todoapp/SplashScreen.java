package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN =2000;

    //variables
    Animation animation;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        //call the animation
        animation= AnimationUtils.loadAnimation(this,R.anim.splash_animation);
        //call the image
        logo = findViewById(R.id.logo);
        //set the animation
        logo.setAnimation(animation);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, IntroActivity.class);
            startActivity(intent);
            finish();
        },SPLASH_SCREEN);

    }
}