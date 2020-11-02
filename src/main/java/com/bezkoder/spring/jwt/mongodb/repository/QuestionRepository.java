package com.bezkoder.spring.jwt.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.models.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {

}
