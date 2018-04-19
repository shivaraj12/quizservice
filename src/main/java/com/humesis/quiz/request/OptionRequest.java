package com.humesis.quiz.request;

public class OptionRequest {
	
	private byte isAnswer;
	private String optionText;
	
	public byte getIsAnswer() {
		return isAnswer;
	}
	public void setIsAnswer(byte isAnswer) {
		this.isAnswer = isAnswer;
	}
	public String getOptionText() {
		return optionText;
	}
	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}
	


}
