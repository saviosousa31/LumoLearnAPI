package com.novacoding.lumolearn_api.LumoLearn.API.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.novacoding.lumolearn_api.LumoLearn.API.model.User;
import com.novacoding.lumolearn_api.LumoLearn.API.model.UserSettings;
import com.novacoding.lumolearn_api.LumoLearn.API.repository.UserRepository;
import com.novacoding.lumolearn_api.LumoLearn.API.repository.UserSettingsRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	UserSettingsRepository userSettingsRepository;

	public Iterable<User> getAllUsers(Integer page, Integer per_page) {		
		int pageNumber = (page != null) ? page : 0;
		int pageSize = (per_page != null) ? per_page : 10;
		
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
		return userRepository.findAll(pageRequest);
	}

	public Optional<User> findUser(Long id, String username) {		
		if(id != null && id > 0)
			return userRepository.findById(id);
		else 
			return userRepository.findByUsername(username);
	}
	
	public User saveUser(User user) {
		// Primeiro, salva o User
		User savedUser = userRepository.save(user);

		if (user.getUser_settings() == null) {
			// Depois cria o settings com o User salvo
			UserSettings settings = new UserSettings();
			settings.setLanguage("pt-br");
			settings.setTheme("light");
			settings.setSound_effects((short) 0);
			settings.setUser(savedUser);
	
			userSettingsRepository.save(settings);
	
			// Atualiza a referência no user (se quiser retornar com tudo preenchido)
			savedUser.setUser_settings(settings);
		}

		return savedUser; 
	}

	public boolean deleteUser(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			return userRepository.findById(id).isEmpty();
		} else
			return false;
	}
}
