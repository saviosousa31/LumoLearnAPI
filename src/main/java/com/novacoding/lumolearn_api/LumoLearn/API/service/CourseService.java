package com.novacoding.lumolearn_api.LumoLearn.API.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.novacoding.lumolearn_api.LumoLearn.API.model.Course;
import com.novacoding.lumolearn_api.LumoLearn.API.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;
	
	public Iterable<Course> getAllCourses(Integer page, Integer per_page) {		
		int pageNumber = (page != null) ? page : 0;
		int pageSize = (per_page != null) ? per_page : 10;
		
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		return courseRepository.findAll(pageRequest);
	}
	
	public Optional<Course> findCourse(Long id, String name){
		if(id != null && id > 0)
			return courseRepository.findById(id);
		else
			return courseRepository.findByName(name);
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
	
	public Iterable<Course> findCourses(Long userId, Long authorId){
		if(userId != null && userId > 0)
			return courseRepository.findByUserId(userId);
		else
			return courseRepository.findByAuthorId(authorId);
	}
}
