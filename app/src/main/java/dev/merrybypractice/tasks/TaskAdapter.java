package dev.merrybypractice.tasks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    public static class TaskHolder extends RecyclerView.ViewHolder {

        public TextView viewTitle;
        public TextView viewDescription;
        public TextView viewState;



        public TaskHolder(@NonNull View itemView) {
            super(itemView);

            this.viewTitle = itemView.findViewById(R.id.view_Title);
            this.viewDescription = itemView.findViewById(R.id.view_Description);
            this.viewState = itemView.findViewById(R.id.view_State);
        }

        public void setTask(dev.merrybypractice.tasks.Task task) {
            ArrayList state = task.getState();
            this.viewTitle.setText(task.getTitle());
            this.viewState.setText(state.get(0).toString());
            this.viewDescription.setText(task.getDescription());
        }
    }

    private ArrayList<Task> displayTasks;

    public TaskAdapter(ArrayList<dev.merrybypractice.tasks.Task> displayTasks) {
        this.displayTasks = displayTasks;
    }

    public void setTasks(ArrayList<dev.merrybypractice.tasks.Task> displayTasks) {
        this.displayTasks = displayTasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_single_task, parent, false);

        TaskHolder holder = new TaskHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        dev.merrybypractice.tasks.Task task = displayTasks.get(position);

        holder.setTask(task);
    }

    @Override
    public int getItemCount() {
        return displayTasks.size();
    }
}
