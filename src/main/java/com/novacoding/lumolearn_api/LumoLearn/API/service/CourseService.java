package com.novacoding.lumolearn_api.LumoLearn.API.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novacoding.lumolearn_api.LumoLearn.API.model.Course;
import com.novacoding.lumolearn_api.LumoLearn.API.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;
	
	public Iterable<Course> getAllCourses(){
		return courseRepository.findAll();
	}
	
	public Optional<Course> findCourse(Long id, String description){
		if(id != null && id > 0)
			return courseRepository.findById(id);
		else
			return courseRepository.findByDescription(description);
	}
	
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}
	
	public boolean deleteCourse(Long id) {
		if(courseRepository.findById(id).isPresent()) {
			courseRepository.deleteById(id);
			return courseRepository.findById(id).isEmpty();
		} else
			return false;
	}
}
