package com.example.todoapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
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
    private String timesStart;
    private String timeEnd;
    @ColumnInfo(name = "state",defaultValue = "0")
    private String state="0";

    public Task(String title, String description, String category) {
        this.title = title;
        this.description = description;
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTimesStart(String timesStart) {
        this.timesStart = timesStart;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
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

    public String getTimesStart() {
        return timesStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public String getState() {
        return state;
    }
}
