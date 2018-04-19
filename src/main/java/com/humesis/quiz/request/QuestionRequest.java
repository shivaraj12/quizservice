package com.humesis.quiz.request;

import java.util.List;

public class QuestionRequest {
	
	private String questionText;
	private int timeLimit;
	private List<OptionRequest> options;
	
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public List<OptionRequest> getOptions() {
		return options;
	}
	public void setOptions(List<OptionRequest> options) {
		this.options = options;
	}

	
}
