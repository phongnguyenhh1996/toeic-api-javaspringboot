package com.bezkoder.spring.jwt.mongodb.constants;

public enum ETestPart {
  PART1(6),
  PART2(25),
  PART3(39),
  PART4(30),
  PART5(30),
  PART6(16),
  PART7(29);

  private int totalQuestions;

  private ETestPart(int totalQuestions) {
    this.totalQuestions = totalQuestions;
  }

  public int getTotalQuestions() {
    return totalQuestions;
  }

  public void setTotalQuestions(int totalQuestions) {
    this.totalQuestions = totalQuestions;
  }
}
