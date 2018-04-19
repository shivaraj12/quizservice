package com.humesis.quiz.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.humesis.quiz.model.Quiz;
import com.humesis.quiz.response.QuizResponse;


@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{
	
	@Query("select new com.humesis.quiz.response.QuizResponse (q.id as id, q.createdOn as createdOn, q.description as description, q.name as name, q.updatedOn as updatedOn) from Quiz q")
	Collection<QuizResponse> listQuiz();

}
