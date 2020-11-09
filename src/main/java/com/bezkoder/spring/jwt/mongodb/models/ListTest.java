package com.bezkoder.spring.jwt.mongodb.models;

import java.util.List;

public class ListTest {
  private List<Test> listTest;

  public List<Test> getListTest() {
    return listTest;
  }

  public void setListTest(List<Test> listTest) {
    this.listTest = listTest;
  }

  public ListTest(List<Test> listTest) {
    this.listTest = listTest;
  }
}
