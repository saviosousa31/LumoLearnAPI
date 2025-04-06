package com.novacoding.lumolearn_api.LumoLearn.API.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.novacoding.lumolearn_api.LumoLearn.API.model.User;
import com.novacoding.lumolearn_api.LumoLearn.API.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> findUser(Long id, String username) {		
		if(id != null && id > 0)
			return userRepository.findById(id);
		else 
			return userRepository.findByUsername(username);
	}
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public boolean deleteUser(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			return userRepository.findById(id).isEmpty();
		} else
			return false;
	}
}
