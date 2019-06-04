package dev.merrybypractice.tasks;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class CreateTask extends AppCompatActivity {
    TextView titleView;
    CheckBox stateAvailable;
    CheckBox stateAccepted;
    CheckBox stateAssigned;
    CheckBox stateFinished;
    TextView descriptionView;

    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        db = FirebaseFirestore.getInstance();

        titleView = findViewById(R.id.input_FormTitle);
        stateAvailable = findViewById(R.id.state_CheckAvaliable);
        stateAccepted = findViewById(R.id.state_CheckAccepted);
        stateAssigned = findViewById(R.id.state_CheckAssigned);
        stateFinished = findViewById(R.id.state_CheckFinished);
        descriptionView = findViewById(R.id.input_Description);


    }


    public void onNewTaskClick(View view) {
        Task newTask = createNewTask();

        db.collection("Tasks")
                .add(newTask)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
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

    public Task createNewTask() {

        Task newTask = new Task();
        newTask.setTitle(titleView.getText().toString());
        newTask.setDescription(descriptionView.getText().toString());

        ArrayList<Integer> stateArray = new ArrayList<>();

        if (stateAccepted.isChecked()) {
            stateArray.add(0);
        }
        if (stateAssigned.isChecked()) {
            stateArray.add(1);
        }

        if (stateAvailable.isChecked()) {
            stateArray.add(2);
        }

        if (stateFinished.isChecked()) {
            stateArray.add(3);
        }

        for (int idx : stateArray) {
            newTask.setState(idx);
        }

        return newTask;

    }
}
