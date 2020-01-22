package com.example.h_wealth.ui.tools;

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
import com.example.h_wealth.quiz;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_tools, container, false);
        final TextView textView = root.findViewById(R.id.tvqz);
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
        final Button bt1=root.findViewById(R.id.btqz);
                bt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        startActivity(new Intent(getActivity(), Quiz01.class)); //ekhn theke quiz starts ??haaa...okkay, how to do this ?done
                        //okay

                    }
                });

            }
        });
        return root;
    }


}