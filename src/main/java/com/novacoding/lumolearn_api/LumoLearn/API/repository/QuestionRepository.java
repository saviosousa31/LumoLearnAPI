package com.novacoding.lumolearn_api.LumoLearn.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.novacoding.lumolearn_api.LumoLearn.API.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
	public Optional<Question> findByDescription(String description);
	
	@Query("SELECT q FROM Question q WHERE q.subject_id = :subjectId")
	public Iterable<Question> findBySubjectId(@Param("subjectId") Long id);
	
	@Query("SELECT q FROM Question q WHERE q.author_id = :authorId")
	public Iterable<Question> findByAuthorId(@Param("authorId") Long id);
}
