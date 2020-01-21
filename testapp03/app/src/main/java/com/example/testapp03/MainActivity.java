package com.example.testapp03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    public static final String fieldemail = "email";
    public static final String fieldpass = "password";
    public  static  final String fieldusername = "username";

    EditText et1, et2,et3;
    Button btn1, btn2;

    private FirebaseFirestore userdb = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlogin();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userreg();
            }
        });

    }


    public void userlogin() {


        final String email = et3.getText().toString();

        final String uname = et1.getText().toString();
        final String pass = et2.getText().toString();
        DocumentReference documentReference = userdb.collection("users").document(uname);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                 String emaildb= documentSnapshot.getString("email");
                String passdb= documentSnapshot.getString("password");

                if( email.equals(emaildb)  && pass.equals(passdb) ){

                    Toast.makeText(MainActivity.this, "gg !!!", Toast.LENGTH_SHORT).show();

                }

                else{
                    Toast.makeText(MainActivity.this, "failed !!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void userreg(){

        final String uname = et1.getText().toString();
        final String pass = et2.getText().toString();
        final String email = et3.getText().toString();


        final Map<Object, String> newuser = new HashMap<>();

        newuser.put(fieldemail, email);
        newuser.put(fieldpass, pass);
        newuser.put(fieldusername, uname);


        userdb.collection("users").document(uname).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
            if(documentSnapshot.exists()){
                Toast.makeText(MainActivity.this, "already registered!! login !!", Toast.LENGTH_SHORT).show();
            } else {

                userdb.collection("users").document(uname).set(newuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "user registered !!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "failed XD !!", Toast.LENGTH_SHORT).show();
                    }
                });



            }
            }
        });

        /*userdb.collection("users").document(uname).set(newuser).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(MainActivity.this, "user registered !!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "failed XD !!", Toast.LENGTH_SHORT).show();
            }
        });  */


    }
}
