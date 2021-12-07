package com.example.todoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MyTaskFragment extends Fragment {

    View myFragment;
    ViewPager viewpager;
    TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate the layout to the fragment
        myFragment = inflater.inflate(R.layout.fragment_my_task,container,false);

        //initilize the viewpager and the tablayout
        viewpager = myFragment.findViewById(R.id.viewPager);
        tabLayout = myFragment.findViewById(R.id.tabLayout);

        //setup the viewpager
        setViewPager();
        //combine the tablayout with the viewpager
        tabLayout.setupWithViewPager(viewpager);

        return myFragment;
    }

    private void setViewPager() {

        //initialize the adapter
        SectionPagerAdapter adapter = new SectionPagerAdapter(getChildFragmentManager());

        //add the pages that we want to add
        adapter.addFragment(new TodayFragment(),"Today");
        adapter.addFragment(new MonthFragment(),"Month");

        //and set theme into the viewpager
        viewpager.setAdapter(adapter);
    }
}