package com.novacoding.lumolearn_api.LumoLearn.API.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.novacoding.lumolearn_api.LumoLearn.API.model.QuestionOptions;
import com.novacoding.lumolearn_api.LumoLearn.API.repository.QuestionOptionsRepository;

@Service
public class QuestionOptionsService {

	@Autowired
	QuestionOptionsRepository questionOptionsRepository;
	
	public Iterable<QuestionOptions> getAllQuestionsOptions(Integer page, Integer per_page) {		
		int pageNumber = (page != null) ? page : 0;
		int pageSize = (per_page != null) ? per_page : 10;
		
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		return questionOptionsRepository.findAll(pageRequest);
	}
	
	public Optional<QuestionOptions> findQuestionOptions(Long id){
		return questionOptionsRepository.findById(id);
	}
	
	public Iterable<QuestionOptions> findQuestionOptionsByQuestionId(Long questionId){
		return questionOptionsRepository.findByQuestionId(questionId);
	}
	
	public QuestionOptions saveQuestionOptions(QuestionOptions questionOptions) {
		return questionOptionsRepository.save(questionOptions);
	}
	
	public boolean deleteQuestionOptions(Long id) {
		if(questionOptionsRepository.findById(id).isPresent()) {
			questionOptionsRepository.deleteById(id);			
			return questionOptionsRepository.findById(id).isEmpty();
		} else
			return false;
	} 
}
