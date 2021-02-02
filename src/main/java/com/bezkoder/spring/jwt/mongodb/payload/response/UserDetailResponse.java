package com.bezkoder.spring.jwt.mongodb.payload.response;

import com.bezkoder.spring.jwt.mongodb.models.User;
import com.bezkoder.spring.jwt.mongodb.models.base.BaseResponse;

public class UserDetailResponse extends BaseResponse<User> {
    public UserDetailResponse(User data){
        super(data);
    }
}
