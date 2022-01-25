package com.example.todoapp.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;

import java.util.List;

public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.TodayHolder>{
    private List<TaskList> categories;
    private Context mContext;
    LayoutInflater mInflater;

    public TodayAdapter(List<TaskList> todayLists) {
        this.categories = todayLists;
    }

    public TodayAdapter(Context context) {
        mContext=context;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TodayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new TodayHolder(itemView);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull TodayHolder holder, int position) {
        if(categories!=null) {
            TaskList category = categories.get(position);
            holder.title.setText(category.getTitle());

            holder.tasks.setHasFixedSize(true);
            TaskAdapter taskAdapter = new TaskAdapter();
            taskAdapter.setTasks(category.getTasks());
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            holder.tasks.setLayoutManager(layoutManager);
            holder.tasks.setAdapter(taskAdapter);
            taskAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        if(categories!=null)
            return categories.size();
        return 0;
    }

    public static class TodayHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final RecyclerView tasks;
        public TodayHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            tasks=itemView.findViewById(R.id.tasks);
        }
    }

    //Associates a list of tasks with this adapter
    public void setCategory(List<TaskList> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }
}
