package com.bezkoder.spring.jwt.mongodb.payload.response;

import com.bezkoder.spring.jwt.mongodb.models.Test;
import com.bezkoder.spring.jwt.mongodb.models.base.BaseResponse;

public class TestDetailResponse extends BaseResponse<Test> {

  public TestDetailResponse(Test data) {
    super(data);
  }

}
