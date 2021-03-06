package com.example.todoapp.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;
    private LiveData<List<Task>> completedTasks;
    private LiveData<List<Task>> inCompletedTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
        completedTasks = repository.getAllTasks();
        inCompletedTasks = repository.getAllTasks();
    }

    public void insert(Task task){
        repository.insert(task);
    }

    public void update(Task task){
        repository.update(task);
    }

    public void delete(Task task){
        repository.delete(task);
    }

    public void deleteAllTasks(){
        repository.deleteAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public LiveData<List<Task>> getCompletedTasks() {
        return completedTasks;
    }

    public LiveData<List<Task>> getIncompletedTasks() {
        return inCompletedTasks;
    }

}