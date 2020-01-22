package com.example.h_wealth.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.h_wealth.Quiz01;
import com.example.h_wealth.R;
import com.example.h_wealth.loginActivity;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;

public class HomeFragment extends Fragment {

    private static  long count = 0;

    public FirebaseFirestore userdb = FirebaseFirestore.getInstance();

    public DocumentReference countref = FirebaseFirestore.getInstance().collection("quiz").document("count");

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        final Button playq = root.findViewById(R.id.btnqz);

        final Button button = root.findViewById(R.id.logout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), loginActivity.class));
            }
        });
        /*final TextView score = root.findViewById(R.id.scoreval);
        score.setText((int) count);*/
        playq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Quiz01.class));
            }
        });
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);


            }
        });
        return root;
    }

    public  void updatecounter(){

        DocumentReference documentReference = userdb.collection("quiz").document("count");
        documentReference.addSnapshotListener((Executor) this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@javax.annotation.Nullable DocumentSnapshot documentSnapshot, @javax.annotation.Nullable FirebaseFirestoreException e) {
                count = (long) documentSnapshot.get("count");

            }
        });
        count = 0;

        countref.update("count", count);
    }

    public static  long getData(){
        return count;
    }
}