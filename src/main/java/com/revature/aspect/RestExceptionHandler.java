package com.revature.aspect;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.revature.errorhandling.ApiError;
import com.revature.errorhandling.ApiValidationError;
import com.revature.exception.UserNotFoundException;


/**
 * All methods in this class will intercept certain exceptions
 * that are sent to the client as HTTP responses from an class
 * annotated with @Controller or @RestController 
 * 
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		// Use apiError object to scrape the status and body of the apiError
		return ResponseEntity.status(apiError.getStatus()).body(apiError);
	}
	
	/**
	 * Intercept exceptions that are caused by validation issues in objects
	 * passed to the controllers add(User u) method
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		// our own custom response
		String error = "Request failed validation.";
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex, error);
		
		// Iterate over fields to return fields that are not valid
		for (FieldError e : ex.getFieldErrors()) {
			// instantiate an ApiValidationError based on the bad field that was passed
			apiError.addSubError(new ApiValidationError(e.getObjectName(), e.getDefaultMessage(), e.getField(), e.getRejectedValue()));
		}
		
		return buildResponseEntity(apiError);
	}
	
	/**
	 * Intercept exceptions from invalid JSON
	 * Send back a 4xx error telling client the problem is on their end
	 * and the server can't read their request 
	 */
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(
			HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		String error = "Malformed JSON request.";
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex, error);

		return buildResponseEntity(apiError);
	}
	
	/**
	 * Intercept a User Not Found from findByUsername() in the controller
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {

		String error = "No user found with that username.";
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex, error);

		return buildResponseEntity(apiError);
	}
	
}
