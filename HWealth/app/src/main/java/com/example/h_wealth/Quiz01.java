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

public class Quiz01 extends AppCompatActivity{

    public  static int count = MainActivity.getData();



    public FirebaseFirestore userdb = FirebaseFirestore.getInstance();
        Button b1,b2,b3,bnext, bhome;
        TextView question;
        int click1 = 0;
        //Boolean click2 = false;
        //Boolean click3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz01);




        bnext = findViewById(R.id.btnnext);
        bhome = findViewById(R.id.btnhome);

        bnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Quiz01.this, "next activity XDD!", Toast.LENGTH_SHORT).show();
                //nextactivity XDD
            }
        });

        bhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz01.this, loginActivity.class));
            }
        });

        b1 = findViewById(R.id.an1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1 = 1;
                String answer = b1.getText().toString();
                checkanswer(answer);

            }
        });

        b2 = findViewById(R.id.an2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // click2 = true;
                String answer = b3.getText().toString();
                checkanswer(answer);
                Toast.makeText(Quiz01.this, "wrong answer !!", Toast.LENGTH_SHORT).show();
            }
        });

        b3 = findViewById(R.id.an3);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // click3 = true;
                String answer = b3.getText().toString();
                checkanswer(answer);
                Toast.makeText(Quiz01.this, "wrong answer !!", Toast.LENGTH_SHORT).show();
            }
        });





    }

    void checkanswer(final String ans){

        DocumentReference documentReference = userdb.collection("quiz").document("q1");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String trueans = documentSnapshot.getString("answer");
                if (ans.equals(trueans)){
                    count ++;
                    Toast.makeText(Quiz01.this, "correct answer !!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Quiz01.this, "you stupid ?", Toast.LENGTH_SHORT).show();

                }
            }
        });
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);

    }
}
