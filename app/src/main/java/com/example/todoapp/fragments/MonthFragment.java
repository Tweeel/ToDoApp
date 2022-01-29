package com.example.todoapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.AddTask;
import com.example.todoapp.R;
import com.example.todoapp.database.Task;
import com.example.todoapp.database.TaskAdapter;
import com.example.todoapp.database.TaskList;
import com.example.todoapp.database.TaskViewModel;
import com.example.todoapp.database.TodayAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MonthFragment extends Fragment {

    private TaskViewModel taskViewModel;
    private TodayAdapter adapterParent;
    private TaskAdapter adapterChild;

    public static final String EXTRA_TITLE = "com.example.todoapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.todoapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_CATEGORY = "com.example.todoapp.EXTRA_CATEGORY";
    public static final String EXTRA_ID = "com.example.todoapp.EXTRA_ID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_month, container, false);

        // Set up the RecyclerView.
        RecyclerView recyclerview = rootView.findViewById(R.id.recycler_view);
        RecyclerView childrecycler = recyclerview.findViewById(R.id.tasks);
        adapterParent = new TodayAdapter(getContext());
        adapterChild = new TaskAdapter();

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(adapterParent);
        taskViewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);

        List<TaskList> categories = new ArrayList<>();
        List<String> dates = new ArrayList<>();

        int catnum=0;

        // Update the cached copy of the words in the adapter.
        // Get all the words from the database
        // and associate them to the adapter.
        taskViewModel.getAllTasks().observe(requireActivity(), tasks -> {
            for (Task task : tasks) {
                dates.add(task.getDate());
            }
            Set<String> datesSet = new HashSet<>(dates);
            dates.clear();
            dates.addAll(datesSet);
            Log.d("test", "dates size = "+String.valueOf(dates.size()));

            for(String date : dates) {
                categories.add(new TaskList(date,null));
            }

            Log.d("test", "categories size = "+String.valueOf(categories.size()));


            for(TaskList tasklist : categories){
                Log.d("test", "tasklist = "+ tasklist.getTitle());
                for(Task task : tasks){
                    Log.d("test", "task = "+ task.getDate());
                    if(task.getDate().equals(tasklist.getTitle())){
                        Log.d("test", "I'm in");
                        List<Task> list = new ArrayList<>();
                        if(tasklist.getTasks()==null){
                            Log.d("test", "if");
                            list.add(task);
                            tasklist.setTasks(list);
                        }
                        else{
                            Log.d("test", "else");
                            list.addAll(tasklist.getTasks());
                            list.add(task);
                            tasklist.setTasks(list);
                        }
                    }
                }
                Log.d("test", "-----------------------------------");
            }

            adapterParent.setCategory(categories);
        });

        //add the onswip to delete the task
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0
                ,ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView
                    , @NonNull RecyclerView.ViewHolder viewHolder
                    , @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                taskViewModel.delete(adapterChild.getTaskAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(),"task has been deleted",Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(childrecycler);

        //add onclick to edit the task
        adapterChild.setOnItemClickListener(new TaskAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(Task task) {
                Intent intent = new Intent(getActivity(), AddTask.class);
                intent.putExtra(EXTRA_TITLE, task.getTitle());
                intent.putExtra(EXTRA_DESCRIPTION, task.getDescription());
                intent.putExtra(EXTRA_CATEGORY, task.getCategory());
                intent.putExtra(EXTRA_ID, Integer.toString(task.getId()));
                Log.d("test", "state  = "+task.getState());
                startActivity(intent);
            }

            @Override
            public void onDoneClick(Task task) {
                if(task.getState().equals("0")){
                    task.setState("1");
                }else{
                    task.setState("0");
                }
                taskViewModel.update(task);
            }
        });

        return rootView;
    }

    //disable the menu
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
}