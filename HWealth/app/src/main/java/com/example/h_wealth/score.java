package com.example.h_wealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class score extends AppCompatActivity {

    public  static String newscore;



    public FirebaseFirestore userdb = FirebaseFirestore.getInstance();
    public DocumentReference countref = FirebaseFirestore.getInstance().collection("quiz").document("count");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score2);


        final TextView textView = findViewById(R.id.scoretv);
        final Button button = findViewById(R.id.home);



        DocumentReference documentReference = userdb.collection("quiz").document("count");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                newscore =  documentSnapshot.get("count").toString();

                textView.setText(newscore);

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(score.this, navDrawer.class));
            }
        });
    }
}
