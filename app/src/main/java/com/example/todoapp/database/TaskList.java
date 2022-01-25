package com.example.todoapp.database;

import androidx.lifecycle.LiveData;

import com.example.todoapp.database.Task;

import java.util.List;

public class TaskList {
    String title;
    private List<Task> tasks;

    public TaskList(String title, List<Task> tasks) {
        this.title = title;
        this.tasks = tasks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
