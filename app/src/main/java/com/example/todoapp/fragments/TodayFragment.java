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
import com.example.todoapp.database.TaskAdapter;
import com.example.todoapp.database.TaskViewModel;

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

    public static final String EXTRA_TITLE = "com.example.todoapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.todoapp.EXTRA_DESCRIPTION";
    public static final String EXTRA_CATEGORY = "com.example.todoapp.EXTRA_CATEGORY";
    public static final String EXTRA_ID = "com.example.todoapp.EXTRA_ID";
    public static final int ADD_NOTE_REQUEST = 1;

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

        // Update the cached copy of the words in the adapter.
        // Get all the words from the database
        // and associate them to the adapter.
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

        //add onclick to edit the task
        adapter.setOnItemClickListener(task -> {
            Intent intent = new Intent(getActivity(), AddTask.class);
            intent.putExtra(EXTRA_TITLE, task.getTitle());
            intent.putExtra(EXTRA_DESCRIPTION, task.getDescription());
            intent.putExtra(EXTRA_CATEGORY, task.getCategory());
            intent.putExtra(EXTRA_ID, Integer.toString(task.getId()));

            Log.d("test", "title want to edit  = "+task.getTitle());
            Log.d("test", "description want to edit  = "+task.getDescription());
            Log.d("test", "category want to edit  = "+task.getCategory());
            Log.d("test", "id want to edit  = "+task.getId());

            startActivity(intent);
        });
        return rootView;
    }
}