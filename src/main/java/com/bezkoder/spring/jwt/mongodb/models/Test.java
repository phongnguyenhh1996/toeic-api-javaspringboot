package com.bezkoder.spring.jwt.mongodb.models;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tests")
public class Test {
  @Id
  private String id;
  private String name;
  private String description;
  private int testType;
  private Integer testPart;
  private int likes;
  private Date createdAt;
  private boolean isOfficial;
  private Integer viewCount;
  private String author;
  private List<String> likeList;
  private HashMap<Integer, Question> questions;
  private HashMap<Integer, List<Answer>> answers;
  private HashMap<Integer, CorrectAnswer> correctAnswer;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getLikes() {
    return likes;
  }

  public void setLikes(int likes) {
    this.likes = likes;
  }

  public Test() {
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isOfficial() {
    return isOfficial;
  }

  public void setOfficial(boolean isOfficial) {
    this.isOfficial = isOfficial;
  }

  public Integer getViewCount() {
    return viewCount;
  }

  public void setViewCount(Integer viewCount) {
    this.viewCount = viewCount;
  }

  public int getTestType() {
    return testType;
  }

  public void setTestType(int testType) {
    this.testType = testType;
  }

  public Integer getTestPart() {
    return testPart;
  }

  public void setTestPart(Integer testPart) {
    this.testPart = testPart;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public HashMap<Integer, Question> getQuestions() {
    return questions;
  }

  public void setQuestions(HashMap<Integer, Question> questions) {
    this.questions = questions;
  }

  public HashMap<Integer, CorrectAnswer> getCorrectAnswer() {
    return correctAnswer;
  }

  public void setCorrectAnswer(HashMap<Integer, CorrectAnswer> correctAnswer) {
    this.correctAnswer = correctAnswer;
  }

  public HashMap<Integer, List<Answer>> getAnswers() {
    return answers;
  }

  public void setAnswers(HashMap<Integer, List<Answer>> answers) {
    this.answers = answers;
  }

  public List<String> getLikeList() {
    return likeList;
  }

  public void setLikeList(List<String> likeList) {
    this.likeList = likeList;
  }

}
