package dev.merrybypractice.tasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//DO THIS INSTEAD OF ALL THAT STRING GRABBING BULLSHIT BEFORE THE INTENT:

//Task task = doc.toObject(Task.class).withId(doc.getId());

//public Task setID(String id){
//this.id=id;
//return this }

public class MainActivity extends AppCompatActivity {

    //firebase level
    FirebaseFirestore db;
    FirebaseUser user;
    private static final int RC_SIGN_IN = 1313;

    //app level
    RecyclerView taskRecycler;
    TaskAdapter tAdapter;
    RecyclerView.LayoutManager taskLayoutManager;
    TextView singleTaskTitle;
    TextView singleTaskDescription;
    RadioButton singleTaskAssigned;
    RadioButton singleTaskFinished;
    TextView viewTitle;
    TextView viewState;
    ArrayList<Task> displayTasks;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //firebase level
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        setUI();

        //app level
        singleTaskTitle = findViewById(R.id.create_Title);
        singleTaskDescription = findViewById(R.id.create_Title);
        singleTaskAssigned = findViewById(R.id.create_Assigned);
        singleTaskFinished = findViewById(R.id.create_Finished);
        context = this;

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

    //auth

    public void onLoginClick(View view) {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build()
        );
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    //TODO: edit to remove a button dependent on state
    private void setUI() {
        Button login = findViewById(R.id.button_login);
        Button logout = findViewById(R.id.button_logout);
        if (user != null) {
            login.setEnabled(false);
            logout.setEnabled(true);
        } else {
            login.setEnabled(true);
            logout.setEnabled(false);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                user = FirebaseAuth.getInstance().getCurrentUser();

                Log.d("MAINACTIVITY", "USER: " + user.getEmail());

            } else {
                Log.e("MAINACTIVITY", "DID NOT RECEIVE USER EEEKK!");
            }

            setUI();
        }
    }

    public void onLogout(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull com.google.android.gms.tasks.Task<Void> task) {
                        Log.d("USER", "Successful Logout");

                    }
                });

        setUI();
    }
}


