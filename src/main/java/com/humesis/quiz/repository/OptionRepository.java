package com.humesis.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humesis.quiz.model.Option;

@Repository
public interface OptionRepository extends JpaRepository<Option, Integer> {

}
