package com.example.quizapp;

import android.content.Context;

import java.util.ArrayList;

public class QuestionsAdapterBuilder {
    private ArrayList<Questions> questions;
    private Context context;

    public QuestionsAdapterBuilder setQuestions(ArrayList<Questions> questions) {
        this.questions = questions;
        return this;
    }

    public QuestionsAdapterBuilder setContext(Context context) {
        this.context = context;
        return this;
    }

    public QuestionsAdapter createQuestionsAdapter() {
        return new QuestionsAdapter(questions, context);
    }
}