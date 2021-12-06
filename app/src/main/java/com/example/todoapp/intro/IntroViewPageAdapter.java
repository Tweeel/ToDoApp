package com.example.todoapp.intro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.todoapp.R;
import com.example.todoapp.intro.IntroSliderItems;

import java.util.List;

public class IntroViewPageAdapter extends PagerAdapter {

    Context mContext;
    List<IntroSliderItems> mScreenItem;

    public IntroViewPageAdapter(Context mContext, List<IntroSliderItems> mScreenItem) {
        this.mContext = mContext;
        this.mScreenItem = mScreenItem;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.slider_screen,null);

        ImageView imgSlid = layoutScreen.findViewById(R.id.succesful_imageView);
        TextView title = layoutScreen.findViewById(R.id.Intro_title);
        TextView description = layoutScreen.findViewById(R.id.intro_description);

        title.setText(mScreenItem.get(position).getTitle());
        description.setText(mScreenItem.get(position).getDiscription());
        Glide.with(mContext).load(mScreenItem.get(position).getScreenIMG()).into(imgSlid);

        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public int getCount() {
        return mScreenItem.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    /**
     * Get the current view position from the ViewPager by
     * extending SimpleOnPageChangeListener class and adding your method
     */
    public static class DetailOnPageChangeListener extends ViewPager.SimpleOnPageChangeListener {

        private int currentPage;
        private ImageView mSportsImage;

        @Override
        public void onPageSelected(int position) {
            currentPage = position;
        }

        public final int getCurrentPage() {
            return currentPage;
        }
    }



}
