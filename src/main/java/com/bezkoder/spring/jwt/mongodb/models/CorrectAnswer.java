package com.bezkoder.spring.jwt.mongodb.models;

public class CorrectAnswer {
    private int answerNumb;
    private String explanation;

    public int getAnswerNumb() {
        return answerNumb;
    }

    public void setAnswerNumb(int answerNumb) {
        this.answerNumb = answerNumb;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public CorrectAnswer(int answerNumb, String explanation) {
        this.answerNumb = answerNumb;
        this.explanation = explanation;
    }
}
