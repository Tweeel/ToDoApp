package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class IntroActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPageAdapter introviewPageAdapter;
    TextView[] dots ;
    LinearLayout dotslayout,buttonsLayout;
    int[] images = {R.drawable.whats_going_to_happen_tomorrow
            , R.drawable.work_happens, R.drawable.tasks_and_assign};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        dotslayout = findViewById(R.id.dots_container);
        buttonsLayout = findViewById(R.id.button);

        //fill list screen
        List<IntroSliderItems> mList = new ArrayList<>();
        mList.add(new IntroSliderItems(R.drawable.whats_going_to_happen_tomorrow,"Welcome to aking"
        ,"What goind to happen tomorrow?"));

        mList.add(new IntroSliderItems(R.drawable.work_happens,"Work happens"
                ,"Get notified when work happens."));

        mList.add(new IntroSliderItems(R.drawable.tasks_and_assign,"Tasks and assign"
                ,"Task and assign them to colleages."));

        //setup viewpager
        screenPager = findViewById(R.id.intro_view_pager);
        introviewPageAdapter = new IntroViewPageAdapter(this,mList);
        screenPager.setAdapter(introviewPageAdapter);

        //setup the dots
        dots = new TextView[3];
        dotsIndicator();

        screenPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

    }

    private void selectedDots(int position) {
        for (int i = 0; i < dots.length; i++) {
            buttonsLayout.setBackgroundResource(images[i]);
            if (i == position) {
                dots[i].setTextColor(getResources().getColor(R.color.black));
            } else {
                dots[i].setTextColor(getResources().getColor(R.color.grey));
            }
        }
    }

    private void dotsIndicator() {
        for(int i=0;i<dots.length;i++){
            if(i==0){
                dots[i] = new TextView(this);
                dots[i].setText(Html.fromHtml("&#9679;"));
                dots[i].setTextColor(getResources().getColor(R.color.black));
                dots[i].setTextSize(18);
                dotslayout.addView(dots[i]);
            }
            else {
                dots[i] = new TextView(this);
                dots[i].setText(Html.fromHtml("&#9679;"));
                dots[i].setTextColor(getResources().getColor(R.color.grey));
                dots[i].setTextSize(18);
                dotslayout.addView(dots[i]);
            }
        }
    }
}