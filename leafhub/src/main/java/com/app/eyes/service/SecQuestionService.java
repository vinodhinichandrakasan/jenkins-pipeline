package com.app.eyes.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.eyes.model.SecQuestions;

@Repository
public interface SecQuestionService {
	@Query(value="from SecQuestions s")
	List<SecQuestions> findAllQues();
}
