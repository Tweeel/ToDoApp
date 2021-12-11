package com.example.todoapp.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Task.class},version = 1)
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase instance;

    public abstract TaskDao taskDao();

    public static synchronized TaskDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TaskDatabase.class,"task_database")
                    // Wipes and rebuilds instead of migrating
                    // if no Migration object.
                    // Migration is not part of this practical.
                    .fallbackToDestructiveMigration()
                    //to implement the first task
                    .addCallback(roomCallback)
                    //build the database
                    .build();
        }
        return instance;
    }

    //this part is to add some task for the first time when the database build
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {

        private TaskDao taskDao;

        private PopulateDbAsyncTask(TaskDatabase db){
            taskDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            taskDao.deleteAllTasks();
            taskDao.insert(new Task("task 1","description 1" , "job"));
            taskDao.insert(new Task("task 2","description 2" , "studies"));
            taskDao.insert(new Task("task 3","description 3" , "random"));

            return null;
        }
    }
}
