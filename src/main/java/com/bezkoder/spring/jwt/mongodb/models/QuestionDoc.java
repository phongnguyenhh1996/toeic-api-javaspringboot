package com.bezkoder.spring.jwt.mongodb.models;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "questions")
public class QuestionDoc {
  @Id
  private String id;
  private String testId;
  private HashMap<Integer, Question> questions;
  private HashMap<Integer, List<Answer>> answers;

  public QuestionDoc() {
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

  public HashMap<Integer, Question> getQuestions() {
    return questions;
  }

  public void setQuestions(HashMap<Integer, Question> questions) {
    this.questions = questions;
  }

  public HashMap<Integer, List<Answer>> getAnswers() {
    return answers;
  }

  public void setAnswers(HashMap<Integer, List<Answer>> answers) {
    this.answers = answers;
  }

}
