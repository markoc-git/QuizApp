package com.example.quizapp;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.QuestionHolder> {
    ArrayList<Questions> questions;
    Context context;
    public QuestionsAdapter(ArrayList<Questions> questions, Context context) {
        this.questions = questions;
        this.context = context;
    }


    @NonNull
    @Override
    public QuestionsAdapter.QuestionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.quiz,parent,false);
        return new QuestionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionsAdapter.QuestionHolder holder, int position) {


        holder.question.setText(questions.get(position).getQuestion());
        holder.correctAnswer.setText(questions.get(position).getCorrectAnswer());
        holder.inCorrect1.setText(questions.get(position).getIncorrectAnswers());
        holder.inCorrect2.setText(questions.get(position).getIncorrectAnswers2());
        holder.inCorrect3.setText(questions.get(position).getIncorrectAnswers3());


    }

    @Override
    public int getItemCount() {
        return questions.size();
    }


    public static class QuestionHolder extends RecyclerView.ViewHolder {
        RadioButton correctAnswer,inCorrect1,inCorrect2,inCorrect3;
        TextView question,pointsText;
        RadioGroup group;

        public QuestionHolder(@NonNull View itemView) {
            super(itemView);
            correctAnswer = itemView.findViewById(R.id.correct);
            inCorrect1 = itemView.findViewById(R.id.inncorect1);
            inCorrect2 = itemView.findViewById(R.id.inncorect2);
            inCorrect3 = itemView.findViewById(R.id.inncorect3);
            question = itemView.findViewById(R.id.question);
            pointsText = itemView.findViewById(R.id.points);
            group = itemView.findViewById(R.id.radioGroup);
        }

    }

}
