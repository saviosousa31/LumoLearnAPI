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

import com.novacoding.lumolearn_api.LumoLearn.API.model.QuestionOptions;
import com.novacoding.lumolearn_api.LumoLearn.API.service.QuestionOptionsService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("api/questionsoptions")
public class QuestionOptionsController {
	@Autowired
	QuestionOptionsService questionOptionsService;
	
	@GetMapping
	public Iterable<QuestionOptions> getAllQuestionsOptions(@RequestParam (required=false) Integer page, 
		  									  @RequestParam (required=false) Integer per_page){
		return questionOptionsService.getAllQuestionsOptions(page, per_page);
	}
	
	@GetMapping("/search")
	public Optional<QuestionOptions> getQuestionOptions(@RequestParam (required=false) Long id){
		return questionOptionsService.findQuestionOptions(id);
	}
	
	@GetMapping("/search2")
	public Iterable<QuestionOptions> getQuestionsOptions(@RequestParam (required=false) Long questionId){
		return questionOptionsService.findQuestionOptionsByQuestionId(questionId);
	}

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody QuestionOptions insertQuestionOptions(@RequestBody @Valid QuestionOptions questionOptions) {
		return questionOptionsService.saveQuestionOptions(questionOptions); 
	}
	
	@DeleteMapping("/delete")
	public boolean deleteQuestionOptions(@RequestParam (required=true) Long id) {
		return questionOptionsService.deleteQuestionOptions(id);
	}

}
