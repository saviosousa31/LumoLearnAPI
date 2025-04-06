package com.novacoding.lumolearn_api.LumoLearn.API.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.novacoding.lumolearn_api.LumoLearn.API.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long>{
	Optional<Subject> findByDescription(String description);	
}
