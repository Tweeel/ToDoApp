package com.example.todoapp.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;
    private LiveData<List<Task>> completedTasks;
    private LiveData<List<Task>> inCompletedTasks;

    public  TaskRepository (Application application){
        TaskDatabase database = TaskDatabase.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
        completedTasks = taskDao.getCompletedTasks();
        inCompletedTasks = taskDao.getIncompletedTasks();
    }

    public void insert(Task task){
        new InssertTaskTask(taskDao).execute(task);
    }
    public void update(Task task){
        new updateTaskTask(taskDao).execute(task);
    }
    public void delete(Task task){
        new DeleteTaskTask(taskDao).execute(task);
    }
    public void deleteAllTasks(){
        new DeleteAllTaskTask(taskDao).execute();
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


    private static class InssertTaskTask extends AsyncTask<Task,Void,Void>{
        private TaskDao taskDao;

        private InssertTaskTask(TaskDao taskDao){
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class updateTaskTask extends AsyncTask<Task,Void,Void>{
        private TaskDao taskDao;

        private updateTaskTask(TaskDao taskDao){
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteTaskTask extends AsyncTask<Task,Void,Void>{
        private TaskDao taskDao;

        private DeleteTaskTask(TaskDao taskDao){
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }

    private static class DeleteAllTaskTask extends AsyncTask<Void,Void,Void>{
        private TaskDao taskDao;

        private DeleteAllTaskTask(TaskDao taskDao){
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.deleteAllTasks();
            return null;
        }
    }
}