package com.example.quizapp;

public class Questions {
    private final String question;
    private final String correctAnswer;
    String incorrectAnswers2;
    String incorrectAnswers3;


    public String getIncorrectAnswers2() {
        return incorrectAnswers2;
    }

    public String getIncorrectAnswers3() {
        return incorrectAnswers3;
    }

    public Questions(String question, String correctAnswer, String incorrectAnswers2, String incorrectAnswers3, String incorrectAnswers) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers2 = incorrectAnswers2;
        this.incorrectAnswers3 = incorrectAnswers3;
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getIncorrectAnswers() {
        return incorrectAnswers;
    }

    private final String incorrectAnswers;

}
