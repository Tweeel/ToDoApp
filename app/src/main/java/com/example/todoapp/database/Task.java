package com.example.todoapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * A basic class representing an entity that is a row in a one-column database table.
 * Entity class that represents a task in the database
 */
@Entity(tableName = "task_table")
public class Task {

    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String description;
    private String category;
    private String date;
    private String time;
    @ColumnInfo(name = "state",defaultValue = "0")
    private String state="0";

    public Task(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }

    @Ignore
    public Task(String title, String description, String category, String date, String time) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.date = date;
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getState() {
        return state;
    }
}
