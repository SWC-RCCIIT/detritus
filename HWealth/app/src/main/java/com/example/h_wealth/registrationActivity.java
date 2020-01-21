package com.example.h_wealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.nio.file.attribute.BasicFileAttributeView;
import java.util.HashMap;
import java.util.Map;

public class registrationActivity extends AppCompatActivity {

    private FirebaseFirestore userdb = FirebaseFirestore.getInstance();

    public static final String fieldusername = "username";

    public static final String fieldcomp = "company";

    public static final String fielddob  = "DOB";


    public static final String fieldfname = "fullname";
    public static final String fieldincome = "annual income";


    public static final String fieldoccup = "occupation";

    public static final String fieldsex = "sex";

    public static final String fieldresponse = "response";

    public static final String fieldpass = "password";

    EditText fullname, company, dob, income, occup, sex, response, username, password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fullname = findViewById(R.id.etfname);
        company = findViewById(R.id.etcomp);
        dob = findViewById(R.id.etdob);
        income = findViewById(R.id.etinc);
        occup = findViewById(R.id.etocc);
        sex = findViewById(R.id.etsex);
        response = findViewById(R.id.etyn);
        username = findViewById(R.id.etuname);
        password = findViewById(R.id.etpass);

        register = findViewById(R.id.btnsubmit);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userreg();
            }
        });
    }


    public void userreg(){

        final String fname = fullname.getText().toString();
        final String pass = password.getText().toString();
        //final String email = et3.getText().toString();

        final String companyname = company.getText().toString();
        final  String dobval = dob.getText().toString();
        final String incomeval = income.getText().toString();
        final String occupation = occup.getText().toString();
        final String s = sex.getText().toString();
        final String resp = response.getText().toString();
        final String uname = username.getText().toString();



        final Map<Object, String> newuser = new HashMap<>();

        //newuser.put(fieldemail, email);
        newuser.put(fieldpass, pass);
        newuser.put(fieldusername, uname);
        newuser.put(fieldcomp, companyname);
        newuser.put(fielddob, dobval);
        newuser.put(fieldincome, incomeval);
        newuser.put(fieldoccup, occupation);
        newuser.put(fieldsex, s);
        newuser.put(fieldresponse, resp);
        newuser.put(fieldfname, fname);


        userdb.collection("users").document(uname).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                    Toast.makeText(registrationActivity.this, "already registered!! login !!", Toast.LENGTH_SHORT).show();
                } else {

                    userdb.collection("users").document(uname).set(newuser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(registrationActivity.this, "user registered !!", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(registrationActivity.this, "failed XD !!", Toast.LENGTH_SHORT).show();
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
