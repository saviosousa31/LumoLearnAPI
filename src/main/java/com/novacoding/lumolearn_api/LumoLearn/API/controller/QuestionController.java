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

import com.novacoding.lumolearn_api.LumoLearn.API.model.Question;
import com.novacoding.lumolearn_api.LumoLearn.API.service.QuestionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("api/questions")
public class QuestionController {
	@Autowired
	QuestionService questionService;
	
	@GetMapping
	public Iterable<Question> getAllQuestions(@RequestParam (required=false) Integer page, 
		  									  @RequestParam (required=false) Integer per_page){
		return questionService.getAllQuestions(page, per_page);
	}
	
	@GetMapping("/search")
	public Optional<Question> getQuestion(@RequestParam (required=false) Long id, 
									   	  @RequestParam (required=false) String name){
		return questionService.findQuestion(id, name);
	}
	
	@GetMapping("/search2")
	public Iterable<Question> getQuestions(@RequestParam (required=false) Long subjectId, 
									   	   @RequestParam (required=false) Long authorId){
		return questionService.findQuestions(subjectId, authorId);
	}

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody Question insertQuestion(@RequestBody @Valid Question question) {
		return questionService.saveQuestion(question); 
	}
	
	@DeleteMapping("/delete")
	public boolean deleteQuestion(@RequestParam (required=true) Long id) {
		return questionService.deleteQuestion(id);
	}

}
