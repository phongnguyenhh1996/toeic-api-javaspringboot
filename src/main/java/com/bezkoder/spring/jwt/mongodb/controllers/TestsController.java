package com.bezkoder.spring.jwt.mongodb.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.bezkoder.spring.jwt.mongodb.constants.Constants;
import com.bezkoder.spring.jwt.mongodb.constants.ETestPart;
import com.bezkoder.spring.jwt.mongodb.constants.ETestType;
import com.bezkoder.spring.jwt.mongodb.models.ListTest;
import com.bezkoder.spring.jwt.mongodb.models.Question;
import com.bezkoder.spring.jwt.mongodb.models.Test;
import com.bezkoder.spring.jwt.mongodb.payload.response.MessageResponse;
import com.bezkoder.spring.jwt.mongodb.payload.response.TestDetailResponse;
import com.bezkoder.spring.jwt.mongodb.payload.response.TestListResponse;
import com.bezkoder.spring.jwt.mongodb.repository.ListTestHomePageRepository;
import com.bezkoder.spring.jwt.mongodb.repository.QuestionRepository;
import com.bezkoder.spring.jwt.mongodb.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
      Question questionExample = new Question();
      questionExample.setTestId(id);
      List<Question> questions = questionRepository.findAll(Example.of(questionExample));
      Test test = new Test();
      if (testData.isPresent()) {
        test = testData.get();
        if (test.getViewCount() == null) {
          test.setViewCount(1);
        } else {
          test.setViewCount(test.getViewCount() + 1);
        }
        testRepository.save(test);
        test.setQuestions(questions);
      }
      return ResponseEntity.ok(new TestDetailResponse(test));
  }

  @PostMapping("/create")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public ResponseEntity<?> createTest(Principal principal, @RequestBody Test test) {
    List<Question> questions = test.getQuestions();
    test.setQuestions(null);
    test.setViewCount(0);
    // Full test validation
    String testType = ETestType.values()[test.getTestType()].toString();
    if (testType.equals(ETestType.FULL.toString())) {
      if (questions.size() != Constants.FULL_TEST_QUESTION_SIZE) {
        return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Full test must be enough " + Constants.FULL_TEST_QUESTION_SIZE + " questions."));
      }
    }
    String userName = principal.getName();
    test.setAuthor(userName);
    // part test validation
    ETestPart testPart = ETestPart.values()[test.getTestPart()];
    if (testPart.getTotalQuestions() != questions.size()) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: "+ testPart +" test must be enough " + testPart.getTotalQuestions() + " questions."));
    }
    test.setCreatedAt(new Date());
    testRepository.save(test);
    questions.forEach(question -> question.setTestId(test.getId()));
    questionRepository.saveAll(questions);
    return ResponseEntity.ok(test);
    
  }
  @GetMapping("/list-homepage")
  public ResponseEntity<?> listTest() {
    List<Test> listTestFull = testRepository.findByTestType(0,
        PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdAt")));
    List<Test> listTestReading = testRepository.findByTestType(1,
        PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdAt")));
    List<Test> listTestListening = testRepository.findByTestType(2,
        PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdAt")));
    ListTest listTestHomePage = new ListTest(listTestFull, listTestReading, listTestListening);
    return ResponseEntity.ok(new ListTestHomePageRepository(listTestHomePage));
  }

}
