package com.bezkoder.spring.jwt.mongodb.controllers;

import java.util.List;
import java.util.Optional;

import com.bezkoder.spring.jwt.mongodb.models.Question;
import com.bezkoder.spring.jwt.mongodb.models.Test;
import com.bezkoder.spring.jwt.mongodb.payload.response.TestDetailResponse;
import com.bezkoder.spring.jwt.mongodb.payload.response.TestListResponse;
import com.bezkoder.spring.jwt.mongodb.repository.QuestionRepository;
import com.bezkoder.spring.jwt.mongodb.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public/tests")
public class TestsController {

  @Autowired
  TestRepository testRepository;
  @Autowired
  QuestionRepository questionRepository;

  @GetMapping("/all")
  public ResponseEntity<?> allTests() {
    List<Test> allTests = testRepository.findAll();
    return ResponseEntity.ok(new TestListResponse(allTests));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> detailTest(@PathVariable("id") String id) {
      Optional<Test> testData = testRepository.findById(id);
      Question questionEx = new Question();
      questionEx.setTestId(id);
      List<Question> questions = questionRepository.findAll(Example.of(questionEx));
      Test test = new Test();
      if (testData.isPresent()) {
        test = testData.get();
        test.setQuestions(questions);
      }
      return ResponseEntity.ok(new TestDetailResponse(test));
  }

}
