package dev.merrybypractice.tasks;

import android.content.Context;
import android.content.Intent;
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
        public TextView viewState;


        public TaskHolder(@NonNull View itemView) {
            super(itemView);

            this.viewTitle = itemView.findViewById(R.id.view_Title);
            this.viewState = itemView.findViewById(R.id.view_State);

        }

        public void setTask(final Task task) {

            this.viewTitle.setText(task.getTitle());
            this.viewState.setText(setState(task));

            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = itemView.getContext();
                    //TODO: Task Details
                    Intent intent = new Intent(context, TaskDetail.class);
                    intent.putExtra("taskid", task.getId());
                    intent.putExtra("State", setState(task));
                    context.startActivity(intent);
                }
            });
        }
    }

    private ArrayList<Task> displayTasks;

    public TaskAdapter(ArrayList<Task> displayTasks) {
        this.displayTasks = displayTasks;
    }

    public void setTasks(ArrayList<Task> displayTasks) {
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
        Task task = displayTasks.get(position);

        holder.setTask(task);
    }

    @Override
    public int getItemCount() {
        return displayTasks.size();
    }


    public static String setState(Task task) {

        String state = " ";

        if (task.getAssigned()) {
            state += "Assigned";
        } else {
            //Add Avaliable to State
            state += "Avaliable";
        }

        if (task.getFinished()) {
            //Add Finished to State
            state += " Finished";
        } else {
            //Add Unfinished to state
            state += " Unfinished";
        }

        return state;
    }

    ;

}