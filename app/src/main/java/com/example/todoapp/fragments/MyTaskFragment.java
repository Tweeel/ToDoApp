package com.example.todoapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.example.todoapp.database.TaskList;
import com.example.todoapp.database.Task;
import com.example.todoapp.database.TaskAdapter;
import com.example.todoapp.database.TaskViewModel;
import com.example.todoapp.database.TodayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * This fragment displays a list of tasks in a RecyclerView.
 * The tasks are saved in a Room database.
 * The layout for this activity also displays a FAB that
 * allows users to start the NewWordActivity to add new words.
 * Users can delete a word by swiping it away, or delete all tasks
 * through the Options menu.
 * Whenever a new task is added, deleted, or updated, the RecyclerView
 * showing the list of words automatically updates.
 */

public class MyTaskFragment extends Fragment {

    private TaskViewModel taskViewModel;
    private TodayAdapter adapterParent;
    private TaskAdapter adapterChild;

    public static final String EXTRA_TITLE = "com.example.todoapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.todoapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_CATEGORY = "com.example.todoapp.EXTRA_CATEGORY";
    public static final String EXTRA_TIME = "com.example.todoapp.EXTRA_TIME";
    public static final String EXTRA_DATE = "com.example.todoapp.EXTRA_DATE";
    public static final String EXTRA_ID = "com.example.todoapp.EXTRA_ID";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_my_task, container, false);

        //get the data from the addFragment in case of adding tasks
        Bundle bundle = this.getArguments();
        //if the bundle is not empty receive the task and create a new task
        if(bundle != null){
            String title = bundle.getString("title");
            String description = bundle.getString("description");
            String category = bundle.getString("category");
            String date = bundle.getString("date");
            String time = bundle.getString("time");
            String id = bundle.getString("id");
            // Set up the RecyclerView.
            TaskViewModel taskViewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);
            //creat a task with the new data
            Task taskAdd = new Task(title, description,category,date,time);
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

        // Set up the RecyclerView.
        RecyclerView recyclerview = rootView.findViewById(R.id.recycler_view);
        RecyclerView childrecycler = recyclerview.findViewById(R.id.tasks);
        adapterParent = new TodayAdapter(getContext());
        adapterChild = new TaskAdapter();

        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(adapterParent);
        taskViewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);

        List<TaskList> categories = new ArrayList<>();
        TaskList completedTasks = new TaskList("Completed Tasks",null);
        TaskList incompletedTasks = new TaskList("Incompleted Tasks",null);
        List<Task> intask = new ArrayList<>();
        List<Task> comtask = new ArrayList<>();


        // Update the cached copy of the words in the adapter.
        // Get all the words from the database
        // and associate them to the adapter.
        taskViewModel.getAllTasks().observe(requireActivity(), tasks -> {
            for (Task task : tasks) {
                if (task.getState().equals("0")) {
                    intask.add(task);
                    incompletedTasks.setTasks(intask);
                } else {
                    comtask.add(task);
                    completedTasks.setTasks(comtask);
                }
            }
            categories.add(incompletedTasks);
            categories.add(completedTasks);

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
                intent.putExtra(EXTRA_TIME, task.getTime());
                intent.putExtra(EXTRA_DATE, task.getDate());
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

}