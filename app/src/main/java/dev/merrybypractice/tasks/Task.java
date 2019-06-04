package dev.merrybypractice.tasks;


import android.content.Context;

import static android.provider.Settings.System.getString;
import static androidx.core.content.res.TypedArrayUtils.getText;

public class Task {

    private String title;

    private String description;

    private String state;

    private Context context;

    private int[] stateList = new int[]{R.string.state_Accepted, R.string.state_Assigned, R.string.state_Available, R.string.state_Finished};

    public Task(String title, int state, String description){
        this.title = title;
        this.state = setState(state);
        this.description = description;
    }

    public Task(){};

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public String setState(int idx) {
        String state = context.getString(stateList[idx]);
        this.state = state;

        return state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
