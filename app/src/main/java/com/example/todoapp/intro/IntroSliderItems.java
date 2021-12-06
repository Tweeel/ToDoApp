package com.example.todoapp.intro;

import android.widget.ImageView;
import android.widget.TextView;

public class IntroSliderItems {
    int ScreenIMG;
    String Title , discription;

    public IntroSliderItems(int screenIMG, String title, String discription) {
        ScreenIMG = screenIMG;
        Title = title;
        this.discription = discription;
    }

    public void setScreenIMG(int screenIMG) {
        ScreenIMG = screenIMG;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getScreenIMG() {
        return ScreenIMG;
    }

    public String getTitle() {
        return Title;
    }

    public String getDiscription() {
        return discription;
    }
}
