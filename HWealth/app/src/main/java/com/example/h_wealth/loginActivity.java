package com.example.h_wealth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class loginActivity extends AppCompatActivity {

    public long qno;

    Button login, fpass;
    EditText username, password;


    public FirebaseFirestore userdb = FirebaseFirestore.getInstance();
    public DocumentReference countref = FirebaseFirestore.getInstance().collection("quiz").document("count");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btnlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlogin();
            }
        });
    }


    public void userlogin() {


        login = findViewById(R.id.btnlogin);
        fpass = findViewById(R.id.btnfpass);

        username = findViewById(R.id.etuname);
        password = findViewById(R.id.etpass);


        //final String email = et3.getText().toString();

        final String uname = username.getText().toString();
        final String pass = password.getText().toString();
        DocumentReference documentReference = userdb.collection("users").document(uname);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                String unamedb= documentSnapshot.getString("username");
                String passdb= documentSnapshot.getString("password");

                if( uname.equals(unamedb)  && pass.equals(passdb) ){

                    //dashboard

                    Toast.makeText(loginActivity.this, "Logged in !!!", Toast.LENGTH_SHORT).show();

                   updatecounter();

                    startActivity(new Intent(loginActivity.this, navDrawer.class));

                }

                if(uname.isEmpty()  || pass.isEmpty()){
                    Toast.makeText(loginActivity.this, "error !!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public  void updatecounter(){

        qno = 1;

        countref.update("count", qno);
    }


}
