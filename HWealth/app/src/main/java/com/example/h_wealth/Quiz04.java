package com.example.h_wealth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class Quiz04 extends AppCompatActivity{
    public  StringBuilder s;
    public  String qno;
    int flag = 0;
    public  static int count = Quiz03.getData();



    public FirebaseFirestore userdb = FirebaseFirestore.getInstance();
    public DocumentReference countref = FirebaseFirestore.getInstance().collection("quiz").document("count");
    Button b10,b11,b12,bnext3, bhome3;
    TextView question;
    int click1 = 0;
    //Boolean click2 = false; so this is final copy , right???
    //yes
    //okay, don't type now and let me do some changes to main java files

    //Boolean click3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz04);


        question = findViewById(R.id.tvq);

        bnext3 = findViewById(R.id.btnq04);
        bhome3 = findViewById(R.id.btnhome);


        bnext3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz04.this, Quiz05.class));
            }
        });

        bhome3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Quiz04.this, navDrawer.class));
            }
        });

        b10 = findViewById(R.id.an1);

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click1 = 1;
                String answer = b10.getText().toString();
                checkanswer(answer);

            }
        });

        b11 = findViewById(R.id.an2);

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click2 = true;
                String answer = b11.getText().toString();
                checkanswer(answer);
                Toast.makeText(Quiz04.this, "wrong answer !!", Toast.LENGTH_SHORT).show();
            }
        });

        b12 = findViewById(R.id.an3);

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click3 = true;
                String answer = b12.getText().toString();
                checkanswer(answer);
                Toast.makeText(Quiz04.this, "wrong answer !!", Toast.LENGTH_SHORT).show();
            }
        });





    }

   /* public  void updatecounter(){

        DocumentReference documentReference = userdb.collection("quiz").document("count");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                qno = (long) documentSnapshot.get("count");

            }
        });
        qno = qno + 1;

        countref.update("count", qno);
    }*/

    public void  updatequestion(){

        DocumentReference documentReference = userdb.collection("quiz").document("count");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                long  qno = (long) documentSnapshot.get("count");

                s = new StringBuilder(100);
                s.append("q");
                s.append(qno);

            }
        });

        DocumentReference documentReference2 = userdb.collection("quiz").document(String.valueOf(s));
        documentReference2.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String opt1= documentSnapshot.getString("ans1");
                String opt2= documentSnapshot.getString("ans2");
                String opt3= documentSnapshot.getString("ans3");

                String newquestion  = documentSnapshot.getString("question");

                b10.setText(opt1);
                b11.setText(opt2);
                b12.setText(opt3);
                question.setText(newquestion);


            }
        });


    }

    void checkanswer(final String ans){

        //  updatequestion();

        DocumentReference documentReference = userdb.collection("quiz").document(String.valueOf("q4"));
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String trueans = documentSnapshot.getString("correct");
                if (ans.equals(trueans)){
                    flag++;
                    updatecounter();
                    Toast.makeText(Quiz04.this, "correct answer !!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(Quiz04.this, "you stupid ?", Toast.LENGTH_SHORT).show();

                }
            }
        });
        b10.setEnabled(false);
        b11.setEnabled(false);
        b12.setEnabled(false);


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
        if(flag == 1)
        qno = qno + 1;

        countref.update("count", 4);
    }

}

