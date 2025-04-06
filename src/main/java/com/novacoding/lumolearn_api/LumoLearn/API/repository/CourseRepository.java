package com.novacoding.lumolearn_api.LumoLearn.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novacoding.lumolearn_api.LumoLearn.API.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{
	Optional<Course> findByDescription(String description);
}
