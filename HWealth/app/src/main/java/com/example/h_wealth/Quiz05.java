package com.example.h_wealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h_wealth.ui.home.HomeFragment;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Quiz05 extends AppCompatActivity{

    public  long qno;
    int flag = 0;
    public  static int count = Quiz04.getData();



    public FirebaseFirestore userdb = FirebaseFirestore.getInstance();

    public DocumentReference countref = FirebaseFirestore.getInstance().collection("quiz").document("count");
    Button b13,b14,b15,bnext4, bhome4;
    TextView question;
    int click1 = 0;
    //Boolean click2 = false;
    //Boolean click3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz05);




        bnext4 = findViewById(R.id.btnq05);
        bhome4 = findViewById(R.id.btnhome);

        bnext4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz05.this, score.class));
            }
        });

        bhome4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz05.this, navDrawer.class));
            }
        });

        b13 = findViewById(R.id.an1);

        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1 = 1;
                String answer = b13.getText().toString();
                checkanswer(answer);

            }
        });

        b14= findViewById(R.id.an2);

        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click2 = true;
                String answer = b14.getText().toString();
                checkanswer(answer);

            }
        });

        b15= findViewById(R.id.an3);

        b15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click3 = true;
                String answer = b15.getText().toString();
                checkanswer(answer);

            }
        });





    }

    void checkanswer(final String ans){

        DocumentReference documentReference = userdb.collection("quiz").document("q5");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String trueans = documentSnapshot.getString("correct");
                if (ans.equals(trueans)){
                    flag++;
                    updatecounter();
                    Toast.makeText(Quiz05.this, "correct answer !!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Quiz05.this, "you stupid ?", Toast.LENGTH_SHORT).show();

                }
            }
        });
        b13.setEnabled(false);
        b14.setEnabled(false);
        b15.setEnabled(false);

    }


    public static  int getData(){
        return count;
    }

    public  void updatecounter(){
        DocumentReference documentReference = userdb.collection("quiz").document("count");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                qno = (long) documentSnapshot.get("count");

            }
        });
        if(flag == 1)
        qno = qno + 1;

        countref.update("count", 5);

    }
}