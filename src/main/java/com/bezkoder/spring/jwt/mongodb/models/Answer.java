package com.bezkoder.spring.jwt.mongodb.models;

public class Answer {
    private int questionNumb;
    private int answerNumb;
    private String answer;

    public int getQuestionNumb() {
        return questionNumb;
    }

    public void setQuestionNumb(int questionNumb) {
        this.questionNumb = questionNumb;
    }

    public int getAnswerNumb() {
        return answerNumb;
    }

    public void setAnswerNumb(int answerNumb) {
        this.answerNumb = answerNumb;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Answer(int questionNumb, int answerNumb, String answer) {
        this.questionNumb = questionNumb;
        this.answerNumb = answerNumb;
        this.answer = answer;
    }
}
