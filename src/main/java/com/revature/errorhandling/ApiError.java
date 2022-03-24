package com.revature.errorhandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * This class models information about an HTTP error
 * 
 */

@Data
public class ApiError {

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	private int status; // error code - 400, 491, 500...
	
	private String error; // general error
	
	private String message;
	
	private String debugMessage; // trace message about error
	
	// List of suberrors
	List<ApiSubError> subErrors = new ArrayList<>();
	
	public ApiError() {
		super();
		this.timestamp = LocalDateTime.now();
	}
	
	public ApiError(HttpStatus status) {
		this(); // invokes constructor with no args
		this.status = status.value();
		this.error = status.getReasonPhrase();
	}
	
	public ApiError(HttpStatus status, Throwable ex) {
		this(status); // calls previous constructor
		this.message = "No message available.";
		this.debugMessage = ex.getLocalizedMessage();
	}
	
	public ApiError(HttpStatus status, Throwable ex, String message) {
		this(status, ex); //  calls previous constructor
		this.message = message;
	}
	
	public void addSubError(ApiSubError err) {
		this.subErrors.add(err);
	}
	
	
	
	
	
	
	
	
	
	
}
