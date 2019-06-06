package dev.merrybypractice.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db;
    RecyclerView taskRecycler;
    TaskAdapter tAdapter;
    RecyclerView.LayoutManager taskLayoutManager;
    TextView singleTaskTitle;
    TextView singleTaskDescription;
    CheckBox singleTaskAvaliable;
    CheckBox singleTaskAccepted;
    CheckBox singleTaskAssigned;
    CheckBox singleTaskFinished;
    ArrayList<Task> displayTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        singleTaskTitle = findViewById(R.id.input_FormTitle);
        singleTaskDescription = findViewById(R.id.input_Description);
        singleTaskAvaliable = findViewById(R.id.state_CheckAvaliable);
        singleTaskAccepted = findViewById(R.id.state_CheckAccepted);
        singleTaskAssigned = findViewById(R.id.state_CheckAssigned);
        singleTaskFinished = findViewById(R.id.state_CheckFinished);

        taskRecycler = findViewById(R.id.task_Recycler);
        displayTasks = getCollection("Tasks");
        taskLayoutManager = new LinearLayoutManager(this);
        taskRecycler.setLayoutManager(taskLayoutManager);

        tAdapter = new TaskAdapter(displayTasks);
        taskRecycler.setAdapter(tAdapter);

    }


    public void onCreateTaskClick(View view) {

        Intent intent = new Intent(this, CreateTask.class);
        startActivity(intent);
    }

    public ArrayList getCollection(String collection) {
        final ArrayList<Task> returnList = new ArrayList<>();
        db.collection("Tasks")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot snap = task.getResult();
                            for (DocumentSnapshot doc : snap.getDocuments()) {
                                Log.d("TASK", "ID: " + doc.getId());
                                Task dbTask = doc.toObject(Task.class);

                                returnList.add(dbTask);

                            }

                            tAdapter.setTasks(returnList);
                        }
                    }
                });
        return returnList;
    }

}


