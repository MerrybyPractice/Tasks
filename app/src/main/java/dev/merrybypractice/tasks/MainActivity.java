package dev.merrybypractice.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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
    RecyclerView.Adapter tAdapter;
    RecyclerView.LayoutManager taskLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        taskRecycler = (RecyclerView) findViewById(R.id.task_Recycler);
        ArrayList<Task> displayTasks = getCollection("Tasks");

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
                        }
                    }
                });
        return returnList;
    }

}


