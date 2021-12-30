package com.example.todoapp.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;

import java.util.List;

/**
 * Adapter for the RecyclerView that displays a list of words.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Task> tasks; // Cached copy of words
    private final LayoutInflater mInflater;
    private OnItemClickListener listener;

    public TaskAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskHolder(itemView);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        if (tasks != null) {
            Task currentTask = tasks.get(position);
            holder.textViewTitle.setText(currentTask.getTitle());
            holder.textViewDescription.setText(currentTask.getDescription());

            switch (currentTask.getCategory()) {
                case "1":
                    holder.layout.setBackgroundResource(R.color.button_beige);
                    if (currentTask.getState().equals("0")){
                        holder.view.setBackgroundResource(R.drawable.uncheck_beige);
                    holder.textViewTitle.setPaintFlags(0);
                    holder.textViewDescription.setPaintFlags(0);
                    }else{
                        holder.view.setBackgroundResource(R.drawable.check_beige);
                        holder.textViewTitle.setPaintFlags(holder.textViewTitle.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                        holder.textViewDescription.setPaintFlags(holder.textViewDescription.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    break;
                case "2":
                    holder.layout.setBackgroundResource(R.color.button_blue);

                    if(currentTask.getState().equals("0")){
                        holder.view.setBackgroundResource(R.drawable.uncheck_blue);
                        holder.textViewTitle.setPaintFlags(0);
                        holder.textViewDescription.setPaintFlags(0);
                    }
                    else{
                        holder.view.setBackgroundResource(R.drawable.check_blue);
                        holder.textViewTitle.setPaintFlags(holder.textViewTitle.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                        holder.textViewDescription.setPaintFlags(holder.textViewDescription.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    break;
                case "3":
                    holder.layout.setBackgroundResource(R.color.button_green);
                    if(currentTask.getState().equals("0")){
                        holder.view.setBackgroundResource(R.drawable.uncheck_green);
                        holder.textViewTitle.setPaintFlags(0);
                        holder.textViewDescription.setPaintFlags(0);
                    }
                    else{
                        holder.view.setBackgroundResource(R.drawable.check_green);
                        holder.textViewTitle.setPaintFlags(holder.textViewTitle.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                        holder.textViewDescription.setPaintFlags(holder.textViewDescription.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                    }                    break;
                case "4":
                    holder.layout.setBackgroundResource(R.color.button_pink);
                    if(currentTask.getState().equals("0")){
                        holder.view.setBackgroundResource(R.drawable.uncheck_pink);
                        holder.textViewTitle.setPaintFlags(0);
                        holder.textViewDescription.setPaintFlags(0);
                    }
                    else{
                        holder.view.setBackgroundResource(R.drawable.check_pink);
                        holder.textViewTitle.setPaintFlags(holder.textViewTitle.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                        holder.textViewDescription.setPaintFlags(holder.textViewDescription.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    break;
                case "5":
                    holder.layout.setBackgroundResource(R.color.button_purple);
                    if(currentTask.getState().equals("0")){
                        holder.view.setBackgroundResource(R.drawable.uncheck_purple);
                        holder.textViewTitle.setPaintFlags(0);
                        holder.textViewDescription.setPaintFlags(0);
                    }
                    else{
                        holder.view.setBackgroundResource(R.drawable.check_purple);
                        holder.textViewTitle.setPaintFlags(holder.textViewTitle.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                        holder.textViewDescription.setPaintFlags(holder.textViewDescription.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    break;
                default:
                    holder.layout.setBackgroundResource(R.color.black);
                    if(currentTask.getState().equals("0")){
                        holder.view.setBackgroundResource(R.drawable.uncheck_black);
                        holder.textViewTitle.setPaintFlags(0);
                        holder.textViewDescription.setPaintFlags(0);
                    }
                    else{
                        holder.view.setBackgroundResource(R.drawable.check_black);
                        holder.textViewTitle.setPaintFlags(holder.textViewTitle.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                        holder.textViewDescription.setPaintFlags(holder.textViewDescription.getPaintFlags()
                                | Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                    break;
            }
        }
    }

    //getItemCount() is called many times, and when it is first called,
    //mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (tasks != null)
            return tasks.size();
        return 0;
    }

    //Associates a list of tasks with this adapter
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    //to get the item from the id
    public Task getTaskAt(int position) {
        return tasks.get(position);
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private View view;
        private LinearLayout layout;
        private RelativeLayout EditLayout;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            layout = itemView.findViewById(R.id.color);
            view = itemView.findViewById(R.id.image);
            EditLayout = itemView.findViewById(R.id.EditLayout);

            EditLayout.setOnClickListener(v -> {
                int position = getAdapterPosition();
                listener.onItemClick(tasks.get(position));
            });

            view.setOnClickListener(v -> {
                int position = getAdapterPosition();
                listener.onDoneClick(tasks.get(position));
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Task task);
        void onDoneClick(Task task);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}