package com.novacoding.lumolearn_api.LumoLearn.API.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.novacoding.lumolearn_api.LumoLearn.API.model.Course;
import com.novacoding.lumolearn_api.LumoLearn.API.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@GetMapping
	public Iterable<Course> getAllCourses(){
		return courseService.getAllCourses();
	}
	
	@GetMapping("/search")
	public Optional<Course> getCourse(@RequestParam (required=false) Long id, String description){
		return courseService.findCourse(id, description);
	}

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody Course insertCourse(@RequestBody @Valid Course course) {
		return courseService.saveCourse(course); 
	}
	
	@DeleteMapping("/delete")
	public boolean deleteCourse(@RequestParam (required=true) Long id) {
		return courseService.deleteCourse(id);
	}
}
