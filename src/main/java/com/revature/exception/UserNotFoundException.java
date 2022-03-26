package com.revature.exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException() {
		super();
	}
	
	// if user does not exist
	public UserNotFoundException(String message) {
		super(message);
		
	}
	
	
}
