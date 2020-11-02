package com.bezkoder.spring.jwt.mongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "questions")
public class Question {
  @Id
  private String id;
  private String testId;
  private Integer type;
  private Integer part;
  private Integer questionNumb;
  private String content;
  private String trueAnswer;
  private String audioUrl;
  private String imageUrl;
  private String answerDescription;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public int getPart() {
    return part;
  }

  public void setPart(int part) {
    this.part = part;
  }

  public int getQuestionNumb() {
    return questionNumb;
  }

  public void setQuestionNumb(int questionNumb) {
    this.questionNumb = questionNumb;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTrueAnswer() {
    return trueAnswer;
  }

  public void setTrueAnswer(String trueAnswer) {
    this.trueAnswer = trueAnswer;
  }

  public String getAudioUrl() {
    return audioUrl;
  }

  public void setAudioUrl(String audioUrl) {
    this.audioUrl = audioUrl;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getAnswerDescription() {
    return answerDescription;
  }

  public void setAnswerDescription(String answerDescription) {
    this.answerDescription = answerDescription;
  }

  public String getTestId() {
    return testId;
  }

  public void setTestId(String testId) {
    this.testId = testId;
  }

  public Question(String testId, Integer type, Integer part, Integer questionNumb, String content, String trueAnswer,
      String audioUrl, String imageUrl, String answerDescription) {
    this.testId = testId;
    this.type = type;
    this.part = part;
    this.questionNumb = questionNumb;
    this.content = content;
    this.trueAnswer = trueAnswer;
    this.audioUrl = audioUrl;
    this.imageUrl = imageUrl;
    this.answerDescription = answerDescription;
  }

  public Question() {
  }

}
