package com.bezkoder.spring.jwt.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.models.TestResultDoc;

public interface TestResultRepository extends MongoRepository<TestResultDoc, String> {
}
