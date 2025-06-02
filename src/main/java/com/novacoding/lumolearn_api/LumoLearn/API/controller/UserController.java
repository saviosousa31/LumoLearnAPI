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

import com.novacoding.lumolearn_api.LumoLearn.API.model.User;
import com.novacoding.lumolearn_api.LumoLearn.API.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public Iterable<User> getAllUsers(@RequestParam (required=false) Integer page, 
									  @RequestParam (required=false) Integer per_page) {
	    return userService.getAllUsers(page, per_page); 
	}
		
	@GetMapping("/search")
	public Optional<User> getUser(@RequestParam (required=false) Long id,
								  @RequestParam (required=false) String username) {
		return userService.findUser(id, username);
	}

	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody User insertUser(@RequestBody @Valid User user) {
		return userService.saveUser(user);
	}
	
	@DeleteMapping("/delete")
	public boolean deleteUser(@RequestParam (required=true) Long id) {
		return userService.deleteUser(id);
	}
} 
