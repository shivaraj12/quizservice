package com.humesis.quiz.response;

import java.util.Date;


public class QuizResponse {
	
	private final int id;

	private final Date  createdOn;

	private final String description;

	private final String name;

	private final Date updatedOn;

	public QuizResponse(int id, Date createdOn, String description, String name, Date updatedOn) {
		super();
		this.id = id;
		this.createdOn = createdOn;
		this.description = description;
		this.name = name;
		this.updatedOn = updatedOn;
	}

	public int getId() {
		return id;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}
	
	
	
	

}
