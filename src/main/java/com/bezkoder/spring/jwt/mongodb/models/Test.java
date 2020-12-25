package com.bezkoder.spring.jwt.mongodb.models;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tests")
public class Test {
  @Id
  private String id;
  private String name;
  private String description;
  private int likes;
  private Date createdAt;
  private boolean isOfficial;
  private List<Question> questions;
  private Integer viewCount;
  private int testType;
  private Integer testPart;

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

  public List<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(List<Question> questions) {
    this.questions = questions;
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

}
