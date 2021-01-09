package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.models.Test;

public interface TestRepository extends MongoRepository<Test, String> {
  List<Test> findAll();

  Optional<Test> findById(String id);

  List<Test> findByTestType(int testType, Pageable page);
}
