package com.example.todoapp.login_regester;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.todoapp.R;

public class SuccesfulActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN =3000;
    Animation animationTop,aminationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succesful);

        ImageView imgSlid = findViewById(R.id.succesful_imageView);
        TextView title = findViewById(R.id.succes_title);
        TextView description = findViewById(R.id.succes_description);
        Glide.with(this).load(R.drawable.seccesful).into(imgSlid);

        //call the animation
        animationTop= AnimationUtils.loadAnimation(this,R.anim.top_succes_animation);
        aminationButton= AnimationUtils.loadAnimation(this,R.anim.buttom_succes_animation);

        //set the animation
        imgSlid.setAnimation(animationTop);
        title.setAnimation(aminationButton);
        description.setAnimation(aminationButton);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SuccesfulActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        },SPLASH_SCREEN);
    }
}