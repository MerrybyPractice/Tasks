package dev.merrybypractice.tasks;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class CreateTask extends AppCompatActivity {
    TextView titleView;
    RadioButton stateAssigned;
    RadioButton stateFinished;
    RadioGroup stateGroup;
    TextView taskState;
    TextView descriptionView;
    String id;


    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        db = FirebaseFirestore.getInstance();

        titleView = findViewById(R.id.create_Title);
        taskState = findViewById(R.id.create_State);
        descriptionView = findViewById(R.id.create_Detail);
        stateAssigned = findViewById(R.id.detail_Assigned);
        stateFinished = findViewById(R.id.detail_Finished);
        stateGroup = findViewById(R.id.create_radioGroup);


    }


    public void onNewTaskClick(View view) {
        final Task newTask = createNewTask(view);

        db.collection("Tasks")
                .add(newTask)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        newTask.setID(documentReference.getId());

                        Log.d("TASK", "Added ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception ex) {
                        Log.d("TASK", "Task failed to add to Database", ex);
                    }
                });
    }

    public Task createNewTask(View view) {

        Task newTask = new Task();
        newTask.setTitle(titleView.getText().toString());
        newTask.setDescription(descriptionView.getText().toString());

        int checked = stateGroup.getCheckedRadioButtonId();

        switch (checked) {
            case R.id.create_Assigned:

                newTask.setAssigned(true);

                break;

            case R.id.create_Finished:

                newTask.setFinished(true);

                break;

        }

        return newTask;

    }


}

