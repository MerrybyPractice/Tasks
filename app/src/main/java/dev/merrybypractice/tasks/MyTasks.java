package dev.merrybypractice.tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import dev.merrybypractice.tasks.R;
import dev.merrybypractice.tasks.Task;
import dev.merrybypractice.tasks.TaskAdapter;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MyTasks extends AppCompatActivity {
    FirebaseUser user;
    FirebaseFirestore db;
    String userID;

    RecyclerView taskRecycler;
    TaskAdapter tAdapter;
    RecyclerView.LayoutManager taskLayoutManager;
    ArrayList<Task> myTasks;
    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tasks);

        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        taskRecycler = findViewById(R.id.user_Recycler);
        myTasks = ;
    }

    public ArrayList getCollection(String collection){
        final ArrayList<Task> returnList = new ArrayList<>();
        db.collection("Users").document(userID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot doc = task.getResult();

                        for(FieldPath fieldPath : doc.get())
                    }

                    }
                })

    }
}
