package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.UserDTO;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService us;

	@Autowired
	public UserController(UserService us) {
		super();
		this.us = us;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		return new ResponseEntity<>(us.getAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable("id") int id, @RequestHeader("Authorization") String token) {
		
		//if token is null, not if it has correct value
		if (token == null) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
			return new ResponseEntity<>(us.getUserById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody User user/*, @RequestHeader String header*/) {
		User u = us.createUser(user);
		return new ResponseEntity<>("User " + u.getUsername() + " was created.", HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") int id) {
		return new ResponseEntity<>(us.updateUser(id,user), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") int id) throws UserNotFoundException {
		us.deleteUser(id);
		return new ResponseEntity<>("User deleted", HttpStatus.OK);
	}
	
	
	
	
}
