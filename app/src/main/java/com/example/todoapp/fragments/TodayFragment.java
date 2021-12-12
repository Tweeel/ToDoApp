package com.example.todoapp.fragments;

import android.os.Bundle;
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

import com.example.todoapp.R;
import com.example.todoapp.database.Task;
import com.example.todoapp.database.TaskAdapter;
import com.example.todoapp.database.TaskViewModel;

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

public class TodayFragment extends Fragment {

    private TaskViewModel taskViewModel;
    private RecyclerView recyclerview;
    private TaskAdapter adapter;
    List<Task> tasks = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_today, container, false);

        // Set up the RecyclerView.
        recyclerview = rootView.findViewById(R.id.recycler_view);
        adapter = new TaskAdapter(getContext());
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(adapter);
        taskViewModel = new ViewModelProvider(requireActivity()).get(TaskViewModel.class);

        //get the data from the addFragment in case of adding tasks
        Bundle bundle = this.getArguments();

        //check if the bundle is not empty
        if(bundle != null){
            String title = bundle.getString("title");
            String description = bundle.getString("description");
            String category = bundle.getString("category");
            int id = bundle.getInt("id");
            Task taskAdd = new Task(title, description,category);
            //check if the id=-1 means that a new task has been created other wise a task has been edited
            if(id!=-1){
                taskAdd.setId(id);
                taskViewModel.update(taskAdd);
                Toast.makeText(getContext(), "task has been edited", Toast.LENGTH_LONG).show();
            }else {
                taskViewModel.insert(taskAdd);
                Toast.makeText(getContext(), "task has been added", Toast.LENGTH_LONG).show();
            }
        }
        
        // Get all the words from the database
        // and associate them to the adapter.
        // Update the cached copy of the words in the adapter.
        taskViewModel.getAllTasks().observe( getActivity(), tasks -> adapter.setTasks(tasks));

        //add the onswip to delete the task
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder
                    , @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    taskViewModel.delete(adapter.getTaskAt(viewHolder.getAdapterPosition()));
                    Toast.makeText(getContext(), "task has been deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerview);

        adapter.setOnItemClickListener(task -> {
            //send data to the today fragment
            Bundle bundle1 = new Bundle();
            bundle1.putString("title",task.getTitle());
            bundle1.putString("description",task.getDescription());
            bundle1.putString("category",task.getCategory());
            bundle1.putInt("id",task.getId());
            AddEditFragment today = new AddEditFragment();
            today.setArguments(bundle1);
            getParentFragmentManager().beginTransaction().replace(R.id.fragmentContainerView,today).commit();
        });

        return rootView;
    }
}