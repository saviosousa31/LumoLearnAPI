package com.novacoding.lumolearn_api.LumoLearn.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.novacoding.lumolearn_api.LumoLearn.API.model.QuestionOptions;

@Repository
public interface QuestionOptionsRepository extends JpaRepository<QuestionOptions, Long> {
	
	@Query("SELECT q FROM QuestionOptions q WHERE q.question_id = :questionId")
	public Iterable<QuestionOptions> findByQuestionId(@Param("questionId") Long id);
}
