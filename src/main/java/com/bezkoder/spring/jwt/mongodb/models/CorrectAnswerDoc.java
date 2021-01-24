package com.bezkoder.spring.jwt.mongodb.models;

import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "correctanswer")
public class CorrectAnswerDoc {
  @Id
  private String id;
  private String testId;
  private HashMap<Integer, CorrectAnswer> correctAnswer;

  public CorrectAnswerDoc() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTestId() {
    return testId;
  }

  public void setTestId(String testId) {
    this.testId = testId;
  }

  public HashMap<Integer, CorrectAnswer> getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(HashMap<Integer, CorrectAnswer> correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

}
