package com.humesis.quiz.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humesis.quiz.model.Option;
import com.humesis.quiz.model.Question;
import com.humesis.quiz.model.Quiz;
import com.humesis.quiz.repository.OptionRepository;
import com.humesis.quiz.repository.QuestionRepository;
import com.humesis.quiz.repository.QuizRepository;
import com.humesis.quiz.request.OptionRequest;
import com.humesis.quiz.request.QuestionRequest;
import com.humesis.quiz.request.QuizRequest;
import com.humesis.quiz.response.QuizResponse;


@Service
public class QuizService {
	
	@Autowired
	QuizRepository quizRepository;
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	OptionRepository optionRepository;
	

	@Transactional
	public Quiz createQuiz(QuizRequest quizRequest) {
		Quiz quiz = new Quiz();
		quiz.setCreatedOn(new Date());
		quiz.setDescription(quizRequest.getDescription());
		quiz.setName(quizRequest.getName());
		quiz.setUpdatedOn(new Date());
		quiz = quizRepository.save(quiz);
		return quiz;
	}
	
	@Transactional
	public Quiz updateQuiz(int quizid, QuizRequest quizRequest) {
		Quiz quiz = quizRepository.findOne(quizid);
		quiz.setDescription(quizRequest.getDescription());
		quiz.setName(quizRequest.getName());
		quiz.setUpdatedOn(new Date());
		quiz = quizRepository.save(quiz);
		return quiz;
	}
	
	@Transactional
	public void deleteQuiz(int quizid) {
	 quizRepository.delete(quizid);
		
	}
	
	@Transactional
	public Collection<QuizResponse> listQuiz() {
		return quizRepository.listQuiz();
	}
	
	
	@Transactional
	public Quiz getQuiz(int quizid) {
		Quiz q = quizRepository.findOne(quizid);
		return q;
	}
	
	
	@Transactional
	public Question  addQuestion(int quizid, QuestionRequest questionRequest) {
		Quiz q =  quizRepository.findOne(quizid);
		Question question = new Question();
		List<Option> ops = new ArrayList<>();
		for(OptionRequest or : questionRequest.getOptions()) {
			Option o = new Option();
			o.setCreatedOn(new Date());
			o.setOptionText(or.getOptionText());
			o.setIsAnswer(or.getIsAnswer());
			o.setUpdatedOn(new Date());
			ops.add(o);
		}
		question.setCreatedOn(new Date());
		question.setQuestionText(questionRequest.getQuestionText());
		question.setUpdatedOn(new Date());
		question.setTimeLimit(questionRequest.getTimeLimit());
		question.setQuiz(q);
		question.setOptions(ops);
		question = questionRepository.save(question);
		return question;
	}
		
	@Transactional
	public Question  updateQuestion(int questionid, QuestionRequest questionRequest) {
		Question question =questionRepository.findOne(questionid);
		question.setQuestionText(questionRequest.getQuestionText());
		question.setUpdatedOn(new Date());
		question.setTimeLimit(questionRequest.getTimeLimit());
		question = questionRepository.save(question);
		return question;
	}
	
	@Transactional
	public void  deleteQuestion(int questionid) {
		questionRepository.delete(questionid);
		
	}
	
	@Transactional
	public void getQuestion(int questionid) {
		questionRepository.findOne(questionid);
		
	}
	

	@Transactional
	public Option addOption(int questionid, OptionRequest optionRequest) {
		Option option = new Option();
		option.setCreatedOn(new Date());
		option.setOptionText(optionRequest.getOptionText());
		option.setIsAnswer(optionRequest.getIsAnswer());
		option.setUpdatedOn(new Date());
		option.setQuestion(questionRepository.getOne(questionid));
		return optionRepository.save(option);
		
	}
	
	@Transactional
	public Option updateOption(int optionid, OptionRequest optionRequest) {
		Option option =  optionRepository.findOne(optionid);
		option.setOptionText(optionRequest.getOptionText());
		option.setIsAnswer(optionRequest.getIsAnswer());
		option.setUpdatedOn(new Date());
		return optionRepository.save(option);
		
	}

	@Transactional
	public void deleteOption(int optionid) {
		 optionRepository.delete(optionid);
		
	}
	
	
}
