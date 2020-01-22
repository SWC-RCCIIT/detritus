package com.example.h_wealth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Quiz03 extends AppCompatActivity{

    public  String qno;
    int flag = 0;
    public  static int count = Quiz02.getData();



    public FirebaseFirestore userdb = FirebaseFirestore.getInstance();

    public DocumentReference countref = FirebaseFirestore.getInstance().collection("quiz").document("count");
    Button b7,b8,b9,bnext2, bhome2;
    TextView question;
    int click1 = 0;
    //Boolean click2 = false;
    //Boolean click3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz03);




        bnext2 = findViewById(R.id.btnq03);
        bhome2= findViewById(R.id.btnhome);

        bnext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz03.this, Quiz04.class));
            }
        });

        bhome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz03.this, navDrawer.class));
            }
        });

        b7 = findViewById(R.id.an1);

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1 = 1;
                String answer = b7.getText().toString();
                checkanswer(answer);

            }
        });

        b8 = findViewById(R.id.an2);

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click2 = true;
                String answer = b8.getText().toString();
                checkanswer(answer);

            }
        });

        b9 = findViewById(R.id.an3);

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click3 = true;
                String answer = b9.getText().toString();
                checkanswer(answer);

            }
        });





    }

    void checkanswer(final String ans){

        DocumentReference documentReference = userdb.collection("quiz").document("q3");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String trueans = documentSnapshot.getString("correct");
                if (ans.equals(trueans)){
                    flag++;
                    updatecounter();
                    Toast.makeText(Quiz03.this, "correct answer !!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Quiz03.this, "you stupid ?", Toast.LENGTH_SHORT).show();

                }
            }
        });
        b7.setEnabled(false);
        b8.setEnabled(false);
        b9.setEnabled(false);

    }


    public static  int getData(){
        return count;
    }

    public  void updatecounter(){
        DocumentReference documentReference = userdb.collection("quiz").document("count");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                qno =  documentSnapshot.get("count").toString();

            }
        });
        if(flag ==1)
        qno = qno + 1;

        countref.update("count", 3);
    }
}