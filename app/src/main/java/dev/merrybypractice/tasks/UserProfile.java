package dev.merrybypractice.tasks;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.appcompat.app.AppCompatActivity;


public class UserProfile extends AppCompatActivity {

    TextView userName;
    TextView email;
    TextView bio;
    String um;
    String em;
    String biography;
    FirebaseUser user;
    FirebaseFirestore db;
    CollectionReference users;
    String id;
    Query thisUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userName = findViewById(R.id.user_UserName);
        email = findViewById(R.id.user_Email);
        bio = findViewById(R.id.user_Bio);
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        users = db.collection("Users");
        id = users.getId();

       /* setText();*/

    }

    public void onSaveTextClick(View view) {
        //need to check firebase if document already exsists
        if (users.whereArrayContains("User Name", user.getDisplayName()).toString().equals(user.getDisplayName())) {
            //if exsists update

            users
                    .document(id)
                    .set(getText());

        } else {

            //if no make new
            User newUser = new User(bio.getText().toString());
            users
                    .add(newUser)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                        }
                    });
        }

    }

    public Object getText() {

        User user = new User(biography);

        this.um = userName.getText().toString();
        this.em = email.getText().toString();
        this.biography = bio.getText().toString();

        return user;
    }

    /*public void setText() {
        userName.setText(user.getDisplayName());
        email.setText(user.getEmail());
        bio.setText(getBio());

    }*/

    public String getBio() {
        String dbBio = users.whereArrayContains("Bio", users.document("id")).toString();
        return dbBio;
    }
}
