package dev.merrybypractice.tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

public class TaskDetail extends AppCompatActivity {

    TextView detailTitle;
    TextView detailState;
    TextView detailDescription;
    RadioButton detailAssigned;
    RadioButton detailFininshed;
    Task thisTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);



        detailTitle = findViewById(R.id.create_Title);
        detailState = findViewById(R.id.create_State);
        detailDescription = findViewById(R.id.create_Detail);
        detailAssigned = findViewById(R.id.detail_Assigned);
        detailFininshed = findViewById(R.id.detail_Finished);

        Intent intent = getIntent();
        detailTitle.setText(intent.getStringExtra("Title"));
        detailDescription.setText(intent.getStringExtra("Description"));
        Boolean assigned = (intent.getBooleanExtra("Assigned", false));
        Boolean finished = (intent.getBooleanExtra("Finished", false));

        String state;

        if(assigned){
            //Add Assigned to State
        } else {
            //Add Avaliable to State
        }

        if(finished){
            //Add Finished to State
        } else {
            //Add Unfinished to state
        }

    }

}
