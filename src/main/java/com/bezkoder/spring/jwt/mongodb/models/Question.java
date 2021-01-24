package com.bezkoder.spring.jwt.mongodb.models;

public class Question {
    private int questionNumb;
    private String question;
    private String imageSrc;
    private String audioSrc;
    private Integer questionGroupId;

    public int getQuestionNumb() {
        return questionNumb;
    }

    public void setQuestionNumb(int questionNumb) {
        this.questionNumb = questionNumb;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getAudioSrc() {
        return audioSrc;
    }

    public void setAudioSrc(String audioSrc) {
        this.audioSrc = audioSrc;
    }

    public Integer getQuestionGroupId() {
        return questionGroupId;
    }

    public void setQuestionGroupId(Integer questionGroupId) {
        this.questionGroupId = questionGroupId;
    }

}
