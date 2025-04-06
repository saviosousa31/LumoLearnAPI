package com.novacoding.lumolearn_api.LumoLearn.API.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novacoding.lumolearn_api.LumoLearn.API.model.Subject;
import com.novacoding.lumolearn_api.LumoLearn.API.repository.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	SubjectRepository subjectRepository;
	
	public Iterable<Subject> getAllSubjects(){
		return subjectRepository.findAll();
	}
	
	public Optional<Subject> findSubject(Long id, String description){
		if(id != null && id > 0)
			return subjectRepository.findById(id);
		else
			return subjectRepository.findByDescription(description);
	}
	
	public Subject saveSubject(Subject subject) {
		return subjectRepository.save(subject);
	}
	
	public boolean deleteSubject(Long id) {
		if(subjectRepository.findById(id).isPresent()) {
			subjectRepository.deleteById(id);
			return subjectRepository.findById(id).isEmpty();
		} else
			return false;
	}
}
