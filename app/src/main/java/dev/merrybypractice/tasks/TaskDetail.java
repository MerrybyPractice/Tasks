package dev.merrybypractice.tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TaskDetail extends AppCompatActivity {

    TextView detailTitle;
    TextView detailState;
    TextView detailDescription;
    RadioButton detailAssigned;
    RadioButton detailFininshed;
    Task thisTask;
    String id;
    String state;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);



        detailTitle = findViewById(R.id.create_Title);
        detailState = findViewById(R.id.create_State);
        detailDescription = findViewById(R.id.create_Detail);
        detailAssigned = findViewById(R.id.detail_Assigned);
        detailFininshed = findViewById(R.id.detail_Finished);
        db = FirebaseFirestore.getInstance();

        Intent intent = getIntent();
        id = intent.getStringExtra("taskid");
        state = intent.getStringExtra("State");
        db.collection("Tasks").document(id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        detailTitle.setText(documentSnapshot.get("title").toString());
                        detailDescription.setText(documentSnapshot.get("description").toString());
                        detailState.setText(state);
                    }
                });

    }

}
