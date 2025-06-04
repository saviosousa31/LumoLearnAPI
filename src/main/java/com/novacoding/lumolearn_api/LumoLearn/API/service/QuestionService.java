package com.novacoding.lumolearn_api.LumoLearn.API.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.novacoding.lumolearn_api.LumoLearn.API.model.Question;
import com.novacoding.lumolearn_api.LumoLearn.API.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	public Iterable<Question> getAllQuestions(Integer page, Integer per_page) {		
		int pageNumber = (page != null) ? page : 0;
		int pageSize = (per_page != null) ? per_page : 10;
		
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		return questionRepository.findAll(pageRequest);
	}
	
	public Optional<Question> findQuestion(Long id, String name){
		if(id != null && id > 0)
			return questionRepository.findById(id);
		else
			return questionRepository.findByName(name);
	}
	
	public Question saveQuestion(Question question) {
		return questionRepository.save(question);
	}
	
	public boolean deleteQuestion(Long id) {
		if(questionRepository.findById(id).isPresent()) {
			questionRepository.deleteById(id);			
			return questionRepository.findById(id).isEmpty();
		} else
			return false;
	}
	
	public Iterable<Question> findQuestions(Long subjectId, Long authorId){
		if(authorId != null && authorId > 0)
			return questionRepository.findByAuthorId(authorId);
		else
			return questionRepository.findBySubjectId(subjectId);
	}
}
