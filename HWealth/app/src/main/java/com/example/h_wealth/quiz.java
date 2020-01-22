package com.example.h_wealth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import javax.annotation.Nullable;

public class quiz extends AppCompatActivity {


    private CollectionReference qref = FirebaseFirestore.getInstance().collection("quiz");

    private myAdapter adapter;

    public FirebaseFirestore userdb = FirebaseFirestore.getInstance();
    Button b1,b2,b3,bnext, bhome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        setView();
    }


    private  void setView(){

        Query query = qref.orderBy("question", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class).build();
        adapter = new myAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.rclv1);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

       /* b1 = findViewById(R.id.btn1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // click1 = 1;
                String answer = b1.getText().toString();
                checkanswer(answer);

            }
        });

        b2 = findViewById(R.id.btn2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click2 = true;
                String answer = b3.getText().toString();
                checkanswer(answer);
               // Toast.makeText(quiz.this, "wrong answer !!", Toast.LENGTH_SHORT).show();
            }
        });

        b3 = findViewById(R.id.btn3);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // click3 = true;
                String answer = b3.getText().toString();
                checkanswer(answer);
               // Toast.makeText(quiz.this, "wrong answer !!", Toast.LENGTH_SHORT).show();
            }
        });

*/
    }

   /* void checkanswer(final String ans){

        DocumentReference documentReference = userdb.collection("quiz").document("q1");
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String trueans = documentSnapshot.getString("correct");
                if (ans.equals(trueans)){
                    //count ++;
                    Toast.makeText(quiz.this, "correct answer !!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(quiz.this, "you stupid ?", Toast.LENGTH_SHORT).show();

                }
            }
        });
        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);

    }*/




    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
//nothing works there, but mine are clickable. will look into this shit, good night
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}


