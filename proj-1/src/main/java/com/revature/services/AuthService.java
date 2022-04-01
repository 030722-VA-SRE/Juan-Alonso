package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dto.UserDTO;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class AuthService {
	private UserRepository ur;

	@Autowired
	public AuthService(UserRepository ur) {
		super();
		this.ur = ur;
	}
	
	public UserDTO login(String username, String password) {
		
		//retrieve user from db uname, returns null if dne
		User principal = ur.findUserByUsername(username);
		
		//checking uname and ppsw sent in request match ones from db
		if(principal == null || !password.equals(principal.getPassword())) {
			//invalid credential/throw exception log
			return null;
	}
		return new UserDTO(principal);
}
	
	public String generateToken(UserDTO principal) {
		//logic to create a token
		return principal.getId() + ":" +principal.getUsername();
	}
	
	
	
	
}