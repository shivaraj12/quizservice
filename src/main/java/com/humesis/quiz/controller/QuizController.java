package com.humesis.quiz.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.humesis.quiz.model.Option;
import com.humesis.quiz.model.Question;
import com.humesis.quiz.model.Quiz;
import com.humesis.quiz.request.OptionRequest;
import com.humesis.quiz.request.QuestionRequest;
import com.humesis.quiz.request.QuizRequest;
import com.humesis.quiz.response.QuizResponse;
import com.humesis.quiz.service.QuizService;

@RestController
public class QuizController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuizController.class);
	
	@Autowired
	QuizService quizService;
	
	/* Creates quiz. 
	 Returns quiz object on success*/
	@RequestMapping(value = "/quiz", method = RequestMethod.POST)
	public ResponseEntity<?> createQuiz(@RequestBody QuizRequest quizRequest) {
		
		Quiz q = quizService.createQuiz(quizRequest);
		LOGGER.info("quiz created with id : "+ q.getId());
		return new ResponseEntity<>(q,HttpStatus.CREATED);
	}

	/*Gets Quiz with questions and options by quiz id*/
	@RequestMapping(value = "/quiz/{quizid}", method = RequestMethod.GET)
	public ResponseEntity<?> getQuiz(@PathVariable int quizid) {
		Quiz q = quizService.getQuiz(quizid);
		return new ResponseEntity<>(q,HttpStatus.OK);
	}
	
	/*Gets list of quiz with quiz description*/
	@RequestMapping(value = "/quiz", method = RequestMethod.GET)
	public ResponseEntity<?> listQuiz() {
		Collection<QuizResponse> q = quizService.listQuiz();
		return new ResponseEntity<>(q,HttpStatus.OK);
	}
	
	
	/*Updates the quiz object identified by quiz id*/
	@RequestMapping(value = "/quiz/{quizid}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateQuiz(@RequestBody QuizRequest quizRequest,@PathVariable int quizid) {
		Quiz q = quizService.updateQuiz(quizid, quizRequest);
		return new ResponseEntity<>(q,HttpStatus.OK);
	}
	
	/*Deletes the quiz object identified by quiz id*/ 
	@RequestMapping(value = "/quiz/{quizid}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteQuiz(@PathVariable int quizid) {
		quizService.deleteQuiz(quizid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/* Creates question with options ( 2 options mandatory) . 
	 Returns question object on success*/
	
	@RequestMapping(value = "quiz/{quizid}/question", method = RequestMethod.POST)
	public ResponseEntity<?> createQuestion(@RequestBody QuestionRequest questionRequest,@PathVariable int quizid) {
		Question q = quizService.addQuestion(quizid, questionRequest);
		return new ResponseEntity<>(q,HttpStatus.CREATED);
	}

	/*Updates the question object identified by question id*/
	@RequestMapping(value = "/question/{questionid}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateQuestion(@RequestBody QuestionRequest questionRequest,@PathVariable int questionid) {
		Question q = quizService.updateQuestion(questionid, questionRequest);
		return new ResponseEntity<>(q,HttpStatus.OK);
	}
	
	/*Deletes the question object identified by question id */ 
	@RequestMapping(value = "/question/{questionid}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteQuestion(@PathVariable int questionid) {
		quizService.deleteQuestion(questionid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/*Add option to question object identified by question id*/
	@RequestMapping(value = "/question/{questionid}/option", method = RequestMethod.POST)
	public ResponseEntity<?> addOption(@PathVariable int questionid,@RequestBody OptionRequest optionRequest) {
		Option o = quizService.addOption(questionid, optionRequest);
		return new ResponseEntity<>(o,HttpStatus.CREATED);
	}
	
	/*Edit option  object identified by option id*/
	@RequestMapping(value = "/option/{optionid}", method = RequestMethod.PUT)
	public ResponseEntity<?> editOption(@RequestBody OptionRequest optionRequest, @PathVariable int optionid) {
		Option o = quizService.updateOption(optionid, optionRequest);
		return new ResponseEntity<>(o,HttpStatus.OK);
	}
	
	/*delete option  object identified by option id*/
	@RequestMapping(value = "/option/{optionid}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteOption(@PathVariable int optionid) {
		quizService.deleteOption(optionid);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

	
}
