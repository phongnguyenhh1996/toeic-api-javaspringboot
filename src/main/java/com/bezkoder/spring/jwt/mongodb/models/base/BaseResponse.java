package com.bezkoder.spring.jwt.mongodb.models.base;

public abstract class BaseResponse<T> {
  private T data;

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public BaseResponse(T data) {
    this.data = data;
  }

}

