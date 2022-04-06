package com.revature.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.UserDTO;
import com.revature.exceptions.UserAlreadyExistsException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.ItemRepository;
import com.revature.repositories.UserRepository;

@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class); 
	
	@Autowired
	private UserRepository ur;
	private ItemRepository ir;
	
	
	public UserService(UserRepository ur, ItemRepository ir) {
		super(); 
		this.ur = ur;
		this.ir = ir;
	}
	

	public UserDTO getUserById(int id) throws UserNotFoundException {
		User user = ur.findById(id).orElseThrow(UserNotFoundException::new);
		return new UserDTO(user);
	}
	
	
	@Transactional
	public User createUser(User newUser) {
//		if (getUserById(newUser.getId()) != null)
//		figure out user not found exception
		return ur.save(newUser);
		
	}
	
	
//	public List<User> getAll(){
//		return ur.findAll();
//	}
	
	
	//Returns users of role ADMIN
	public List<UserDTO> getUsers() {
		List<User> users = ur.findAll();
		
		List<UserDTO> userDto = users.stream()
//				.filter(user -> user.getRole().toString().equals("ADMIN"))
				.map((user) -> new UserDTO(user))
				.collect(Collectors.toList());
		return userDto;
	}
	
	
	@Transactional
	public User updateUser(int id, User user) {
		//log for update user
		//check if exists
		return ur.save(user);
	}
	
	@Transactional
	public void deleteUser(int id) throws UserNotFoundException {
		
		getUserById(id);
		
		ur.deleteById(id);
	}
	
	
	
	
	
	
	
}
