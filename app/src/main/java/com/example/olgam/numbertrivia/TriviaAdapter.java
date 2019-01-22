package com.example.olgam.numbertrivia;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.parseColor;

public class TriviaAdapter extends RecyclerView.Adapter<TriviaAdapter.MyViewHolder>{

    ArrayList<Trivia> mTriviaItems;

    public TriviaAdapter(ArrayList<Trivia> mTriviaItems) {
        this.mTriviaItems=mTriviaItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_view, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Trivia triviaItem =  mTriviaItems.get(position);
        //holder.itemView.findViewById(R.id.triviaText);
        holder.number.setText(triviaItem.getNumber());
        holder.triviaText.setText(triviaItem.getText());

        ConstraintLayout layout = holder.itemView.findViewById(R.id.main);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);

        if (position  % 2 == 0)
        {
            constraintSet.connect(R.id.triviaText,ConstraintSet.START,R.id.guideline_left,ConstraintSet.START,0);
            constraintSet.connect(R.id.numberText,ConstraintSet.START,R.id.parent,ConstraintSet.START,0);
            constraintSet.applyTo(layout);

        } else {
            constraintSet.applyTo(layout);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        if (mTriviaItems == null)
            return 0;
        else
            return mTriviaItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        final ConstraintLayout cv;
        final TextView triviaText;
        final TextView number;
        private final ConstraintLayout main;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cardView);
            triviaText = itemView.findViewById(R.id.triviaText);
            number = itemView.findViewById(R.id.numberText);
            main = itemView.findViewById(R.id.main);        }
    }

}
