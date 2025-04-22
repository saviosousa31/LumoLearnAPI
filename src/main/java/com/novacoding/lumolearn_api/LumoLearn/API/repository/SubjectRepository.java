package com.novacoding.lumolearn_api.LumoLearn.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.novacoding.lumolearn_api.LumoLearn.API.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
	public Optional<Subject> findByDescription(String description);
	
	@Query("SELECT s FROM Subject s WHERE s.course_id = :courseId")
	public Iterable<Subject> findByCourseId(@Param("courseId") Long courseId);
}
