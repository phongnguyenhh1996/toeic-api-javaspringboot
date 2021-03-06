package com.bezkoder.spring.jwt.mongodb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.bezkoder.spring.jwt.mongodb.models.Test;

public interface TestRepository extends MongoRepository<Test, String> {
  List<Test> findAll();

  Optional<Test> findById(String id);

  List<Test> findByTestType(int testType, Pageable page);

  Page<Test> findByTestPart(Integer testPart, Pageable page);

  List<Test> findByTestPartBetween(int testPartGT, int testPartLT, Pageable page);

  Page<Test> findByAuthor(String userName, Pageable pageable);

  Page<Test> findByAuthorAndTestPart(String userName, Integer testPart, Pageable pageable);

  Page<Test> findAll(Pageable pageable);

  Page<Test> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
