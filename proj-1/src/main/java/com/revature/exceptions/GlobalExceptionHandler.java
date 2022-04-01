package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No user with given id found")
	@ExceptionHandler(UserNotFoundException.class)
	public void handleUserNotFoundException() {
		//logg
	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="No item with given id found")
	@ExceptionHandler(ItemNotFoundException.class)
	public void handleItemNotFoundException() {
		//log too
	}
	
	
	
}
