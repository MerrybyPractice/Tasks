package dev.merrybypractice.tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CreateTask extends AppCompatActivity {
    TextView titleView;
    CheckBox stateAvailable;
    CheckBox stateAccepted;
    CheckBox stateAssigned;
    CheckBox stateFinished;
    TextView descriptionView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        titleView = findViewById(R.id.input_FormTitle);
        stateAvailable = findViewById(R.id.state_CheckAvaliable);
        stateAccepted = findViewById(R.id.state_CheckAccepted);
        stateAssigned = findViewById(R.id.state_CheckAssigned);
        stateFinished = findViewById(R.id.state_CheckFinished);
        descriptionView = findViewById(R.id.input_Description);


        }
    }

    public void onNewTaskClick(View view){
        Task newTask = new Task();
        newTask.setTitle(titleView.getText().toString());
        newTask.setDescription(descriptionView.getText().toString());

        ArrayList<Integer> stateArray = new ArrayList<>();

       if(stateAccepted.isChecked()){
           stateArray.add(0);
    }
        if(stateAssigned.isChecked()){
            stateArray.add(1);
        }

        if(stateAvailable.isChecked()){
            stateArray.add(2);
        }

        if(stateFinished.isChecked()){
            stateArray.add(3);
        }

        for(int idx : stateArray){
            newTask.setState(idx);
        }
}
