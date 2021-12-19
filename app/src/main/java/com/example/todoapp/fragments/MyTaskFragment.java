package com.example.todoapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.todoapp.R;
import com.example.todoapp.database.Task;
import com.example.todoapp.database.TaskViewModel;
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

        //get the data from the addFragment in case of adding tasks
        Bundle bundle = this.getArguments();
        //if the bundle is not empty receive the task and create a new task
        if(bundle != null){
            String title = bundle.getString("title");
            String description = bundle.getString("description");
            String category = bundle.getString("category");
            String id = bundle.getString("id");
            Log.d("test", "id in my task = " + id);
            // Set up the RecyclerView.
            TaskViewModel taskViewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
            //creat a task with the new data
            Task taskAdd = new Task(title, description,category);
            if(id.equals("0")){
                //add the task
                taskViewModel.insert(taskAdd);
                //toast of confirmation
                Toast.makeText(getContext(), "task has been added", Toast.LENGTH_LONG).show();
            }else{
                taskAdd.setId(Integer.parseInt(id));
                //add the task
                taskViewModel.update(taskAdd);
                //toast of confirmation
                Toast.makeText(getContext(),"task has been updated", Toast.LENGTH_LONG).show();
            }
        }

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