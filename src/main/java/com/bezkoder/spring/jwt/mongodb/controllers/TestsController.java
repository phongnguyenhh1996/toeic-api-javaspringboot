package com.bezkoder.spring.jwt.mongodb.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.bezkoder.spring.jwt.mongodb.constants.Constants;
import com.bezkoder.spring.jwt.mongodb.constants.ETestPart;
import com.bezkoder.spring.jwt.mongodb.constants.ETestType;
import com.bezkoder.spring.jwt.mongodb.models.Answer;
import com.bezkoder.spring.jwt.mongodb.models.CorrectAnswer;
import com.bezkoder.spring.jwt.mongodb.models.CorrectAnswerDoc;
import com.bezkoder.spring.jwt.mongodb.models.ListTest;
import com.bezkoder.spring.jwt.mongodb.models.Question;
import com.bezkoder.spring.jwt.mongodb.models.QuestionDoc;
import com.bezkoder.spring.jwt.mongodb.models.Test;
import com.bezkoder.spring.jwt.mongodb.payload.response.MessageResponse;
import com.bezkoder.spring.jwt.mongodb.payload.response.TestDetailResponse;
import com.bezkoder.spring.jwt.mongodb.payload.response.TestListResponse;
import com.bezkoder.spring.jwt.mongodb.repository.CorrectAnswerRepository;
import com.bezkoder.spring.jwt.mongodb.repository.ListTestHomePageRepository;
import com.bezkoder.spring.jwt.mongodb.repository.QuestionRepository;
import com.bezkoder.spring.jwt.mongodb.repository.TestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/public/tests")
public class TestsController {

  @Autowired
  TestRepository testRepository;
  @Autowired
  QuestionRepository questionRepository;
  @Autowired
  CorrectAnswerRepository correctAnswerRepository;

  @GetMapping("/all")
  public ResponseEntity<?> allTests() {
    List<Test> allTests = testRepository.findAll();
    return ResponseEntity.ok(new TestListResponse(allTests));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> detailTest(@PathVariable("id") String id) {
      Optional<Test> testData = testRepository.findById(id);
      Optional<QuestionDoc> questions = questionRepository.findByTestId(id);
      Optional<CorrectAnswerDoc> correctAnswer = correctAnswerRepository.findByTestId(id);


      Test test = new Test();
      if (testData.isPresent()) {
        test = testData.get();
        if (test.getViewCount() == null) {
          test.setViewCount(1);
        } else {
          test.setViewCount(test.getViewCount() + 1);
        }
        testRepository.save(test);
        if (questions.isPresent()) {
          test.setQuestions(questions.get().getQuestions());
          test.setAnswers(questions.get().getAnswers());
        }
        if (correctAnswer.isPresent()) {
          test.setCorrectAnswer(correctAnswer.get().getCorrectAnswer());
        }
      }
      return ResponseEntity.ok(new TestDetailResponse(test));
  }

  @PostMapping("/create")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public ResponseEntity<?> createTest(Principal principal, @RequestBody Test test) {
    //separate question, answer, correctAnswer

    HashMap<Integer, Question> questions = test.getQuestions();
    HashMap<Integer, List<Answer>> answers = test.getAnswers();
    HashMap<Integer, CorrectAnswer> correctAnswer = test.getCorrectAnswer();

    test.setQuestions(null);
    test.setAnswers(null);
    test.setCorrectAnswer(null);
    
    // Full test validation
    String testType = ETestType.values()[test.getTestType()].toString();
    if (testType.equals(ETestType.FULL.toString())) {
      if (questions.size() != Constants.FULL_TEST_QUESTION_SIZE) {
        return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Full test must be enough " + Constants.FULL_TEST_QUESTION_SIZE + " questions."));
      }
    }


    // part test validation
    ETestPart testPart = ETestPart.values()[test.getTestPart()];
    if (testPart.getTotalQuestions() != questions.size()) {
      return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: "+ testPart +" test must be enough " + testPart.getTotalQuestions() + " questions."));
    }

    
    // set author
    String userName = principal.getName();
    test.setAuthor(userName);

    //init some values and save test
    test.setViewCount(0);
    test.setCreatedAt(new Date());

    testRepository.save(test);
    
    // save question
    QuestionDoc questionDoc = new QuestionDoc();
    questionDoc.setTestId(test.getId());
    questionDoc.setQuestions(questions);
    questionDoc.setAnswers(answers);

    questionRepository.save(questionDoc);

    // save correctAnswer
    CorrectAnswerDoc correctAnswerDoc = new CorrectAnswerDoc();
    correctAnswerDoc.setTestId(test.getId());
    correctAnswerDoc.setCorrectAnswer(correctAnswer);

    correctAnswerRepository.save(correctAnswerDoc);

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
  @GetMapping("/created")
  public ResponseEntity<?> listCreated(Principal principal){
    String userName = principal.getName();
    List<Test> listTestCreated = testRepository.findByAuthor(userName);
    return ResponseEntity.ok(new TestListResponse(listTestCreated));
  }
  @GetMapping("/testsPage")
  public ResponseEntity<Map<String, Object>> getAllTestsPage(
      @RequestParam(required = false) String name,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "3") int size) {

    try {
      List<Test> tests = new ArrayList<Test>();
      Pageable paging = PageRequest.of(page, size);
      
      Page<Test> pageTuts;
      if (name == null)
        pageTuts = testRepository.findAll(paging);
      else
        pageTuts = testRepository.findByNameContainingIgnoreCase(name, paging);

        tests = pageTuts.getContent();

      Map<String, Object> response = new HashMap<>();
      response.put("tests", tests);
      response.put("currentPage", pageTuts.getNumber());
      response.put("totalItems", pageTuts.getTotalElements());
      response.put("totalPages", pageTuts.getTotalPages());

      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
