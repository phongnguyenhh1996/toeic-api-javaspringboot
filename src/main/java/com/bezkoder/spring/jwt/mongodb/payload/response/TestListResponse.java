package com.bezkoder.spring.jwt.mongodb.payload.response;

import java.util.List;

import com.bezkoder.spring.jwt.mongodb.models.Test;
import com.bezkoder.spring.jwt.mongodb.models.base.BaseResponse;

public class TestListResponse extends BaseResponse<List<Test>> {

  public TestListResponse(List<Test> data) {
    super(data);
  }

}
