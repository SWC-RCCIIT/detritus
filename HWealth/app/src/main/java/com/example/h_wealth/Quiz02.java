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

public class Quiz02 extends AppCompatActivity{

    public  String qno;
    int flag = 0;
    public  static int count = Quiz01.getData();



    public FirebaseFirestore userdb = FirebaseFirestore.getInstance();

    public DocumentReference countref = FirebaseFirestore.getInstance().collection("quiz").document("count");
    Button b4,b5,b6,bnext1, bhome1;
    TextView question;
    int click1 = 0;
    //Boolean click2 = false;
    //Boolean click3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz02);




        bnext1 = findViewById(R.id.btnq02);
        bhome1 = findViewById(R.id.btnhome);

        bnext1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz02.this, Quiz03.class));
            }
        });

        bhome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz02.this, navDrawer.class));
            }
        });

        b4 = findViewById(R.id.an1);

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1 = 1;
                String answer = b4.getText().toString();
                checkanswer(answer);

            }
        });

        b5 = findViewById(R.id.an2);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click2 = true;
                String answer = b5.getText().toString();
                checkanswer(answer);

            }
        });

        b6 = findViewById(R.id.an3);

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click3 = true;
                String answer = b6.getText().toString();
                checkanswer(answer);

            }
        });





    }

    void checkanswer(final String ans){

        DocumentReference documentReference = userdb.collection("quiz").document("q2");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String trueans = documentSnapshot.getString("correct");
                if (ans.equals(trueans)){
                    flag++;
                    updatecounter();
                    Toast.makeText(Quiz02.this, "correct answer !!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Quiz02.this, "you stupid ?", Toast.LENGTH_SHORT).show();

                }
            }
        });
        b4.setEnabled(false);
        b5.setEnabled(false);
        b6.setEnabled(false);

    }


    public static  int getData(){
        return count;
    }
    public  void updatecounter(){
        DocumentReference documentReference = userdb.collection("quiz").document("count");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                qno = documentSnapshot.get("count").toString();

            }
        });
        if(flag == 1)
        qno = qno + 1;

        countref.update("count", 2);
    }
}
