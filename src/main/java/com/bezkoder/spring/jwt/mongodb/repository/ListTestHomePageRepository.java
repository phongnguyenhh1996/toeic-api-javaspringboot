package com.bezkoder.spring.jwt.mongodb.repository;

import com.bezkoder.spring.jwt.mongodb.models.ListTest;
import com.bezkoder.spring.jwt.mongodb.models.base.BaseResponse;

public class ListTestHomePageRepository extends BaseResponse<ListTest>{

    public ListTestHomePageRepository(ListTest data) {
        super(data);
    }
    
}
