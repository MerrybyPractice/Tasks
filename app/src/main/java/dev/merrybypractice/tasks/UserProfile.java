package dev.merrybypractice.tasks;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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
    DocumentReference userDoc;
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
        id = user.getUid();
        userDoc = db.collection("Users").document("F094q5wAxxOaslDIyd5XBhiTdU62");

        setText();

    }

    public void onSaveTextClick(View view) {
        setBio();
        setText();

    }


    public void setText() {

        userDoc
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        userName.setText((documentSnapshot.get("name").toString()));
                        email.setText(documentSnapshot.get("email").toString());
                        if (documentSnapshot.get("bio") != null) {
                            bio.setText(documentSnapshot.get("bio").toString());
                            Log.d("BIO", "BIO FROM DB");
                        } else {
                            bio.setText("Update your Bio Here!");
                        }

                    }
                });


    }

    //TODO: STOP IT FROM PULLING A STRINGY QUERY
    public String setBio() {


        final String userBio;
        db.collection("Users").document("F094q5wAxxOaslDIyd5XBhiTdU62")
                .update("bio", bio.getText().toString(),
                        "name", userName.getText().toString(),
                        "email", email.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("BIO", "Bio Updated");
                    }
                });
        return bio.getText().toString();
    }
}
