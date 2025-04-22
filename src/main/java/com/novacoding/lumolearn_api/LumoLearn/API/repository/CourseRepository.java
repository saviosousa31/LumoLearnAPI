package com.novacoding.lumolearn_api.LumoLearn.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.novacoding.lumolearn_api.LumoLearn.API.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	public Optional<Course> findByName(String name);
	
	@Query("SELECT c FROM Course c WHERE c.user_id = :userId")
	public Iterable<Course> findByUserId(@Param("userId") Long userId);
	
	@Query("SELECT c FROM Course c WHERE c.author_id = :authorId")
	public Iterable<Course> findByAuthorId(@Param("authorId") Long authorId);
}
