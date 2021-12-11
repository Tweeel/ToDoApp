package com.example.todoapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
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
        // Get all the words from the database
        // and associate them to the adapter.
        // Update the cached copy of the words in the adapter.
        taskViewModel.getAllTasks().observe((LifecycleOwner) getContext(), new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                adapter.setTasks(tasks);
            }
        });

        return rootView;
    }
}