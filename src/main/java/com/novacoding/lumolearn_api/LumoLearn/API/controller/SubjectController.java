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

import com.novacoding.lumolearn_api.LumoLearn.API.model.Subject;
import com.novacoding.lumolearn_api.LumoLearn.API.service.SubjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

	@Autowired
	private SubjectService subjectService;
	
	@GetMapping
	public Iterable<Subject> getAllSubjects(@RequestParam (required=false) Integer page, Integer per_page){
		return subjectService.getAllSubjects(page, per_page);
	}
	
	@GetMapping("/search")
	public Optional<Subject> getSubject(@RequestParam (required=false) Long id, String description){
		return subjectService.findSubject(id, description);
	}
	
	@GetMapping("/search/course")
	public Iterable<Subject> getSubjectsByCourseId(@RequestParam (required=true) Long courseId){
		return subjectService.findSubjectsByCourseId(courseId);
	}
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody Subject insertSubject(@RequestBody @Valid Subject subject) {
		return subjectService.saveSubject(subject);
	}
	
	@DeleteMapping("/delete")
	public boolean deleteSubject(@RequestParam (required=true) Long id) {
		return subjectService.deleteSubject(id);
	}
}
