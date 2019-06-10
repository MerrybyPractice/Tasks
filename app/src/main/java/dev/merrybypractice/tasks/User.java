package dev.merrybypractice.tasks;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class User {
    String name;
    String email;
    Uri photoUrl;
    String uid;
    String bio;
    ArrayList <String> deviceID = new ArrayList<>();
    Context context;


    User(String bio) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            this.name = user.getDisplayName();
            this.email = user.getEmail();
            this.photoUrl = user.getPhotoUrl();
            this.uid = user.getUid();
            this.bio = bio;
        } else {
            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
        }
    }


}


