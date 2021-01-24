package com.bezkoder.spring.jwt.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

import com.bezkoder.spring.jwt.mongodb.models.CorrectAnswerDoc;

public interface CorrectAnswerRepository extends MongoRepository<CorrectAnswerDoc, String> {
    Optional<CorrectAnswerDoc> findByTestId(String testId);
}
