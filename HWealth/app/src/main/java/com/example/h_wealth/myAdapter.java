package com.example.h_wealth;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class myAdapter extends  FirestoreRecyclerAdapter<Note, myAdapter.myHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public myAdapter(@NonNull FirestoreRecyclerOptions<Note> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myAdapter.myHolder holder, int position, @NonNull Note model) {
        holder.question.setText(model.getQuestion());
        holder.ans1.setText(model.getAns1());
        holder.ans2.setText(model.getAns2());
        holder.ans3.setText(model.getAns3());

    }

    @NonNull
    @Override
    public myAdapter.myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);

        return new myAdapter.myHolder(v);
    }

    public class myHolder extends RecyclerView.ViewHolder{

        TextView question;
        TextView ans1, ans2, ans3;
        public myHolder(@NonNull View itemView) {
            super(itemView);
            question = itemView.findViewById(R.id.tvques);
            ans1 = itemView.findViewById(R.id.btn1);
            ans2 = itemView.findViewById(R.id.btn2);
            ans3 = itemView.findViewById(R.id.btn3);
        }
    }
}
