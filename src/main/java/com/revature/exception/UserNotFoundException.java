package com.revature.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {
	
	// if user does not exist
	public UserNotFoundException(String message) {
		super(message);
		
	}
	
}
