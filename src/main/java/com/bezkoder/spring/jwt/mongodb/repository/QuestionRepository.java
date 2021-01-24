package com.bezkoder.spring.jwt.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

import com.bezkoder.spring.jwt.mongodb.models.QuestionDoc;

public interface QuestionRepository extends MongoRepository<QuestionDoc, String> {
    Optional<QuestionDoc> findByTestId(String testId);

}
