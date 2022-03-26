package com.revature.errorhandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This class models information about an HTTP error
 * 
 */

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

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public List<ApiSubError> getSubErrors() {
		return subErrors;
	}

	public void setSubErrors(List<ApiSubError> subErrors) {
		this.subErrors = subErrors;
	}

	@Override
	public int hashCode() {
		return Objects.hash(debugMessage, error, message, status, subErrors, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ApiError other = (ApiError) obj;
		return Objects.equals(debugMessage, other.debugMessage) && Objects.equals(error, other.error)
				&& Objects.equals(message, other.message) && status == other.status
				&& Objects.equals(subErrors, other.subErrors) && Objects.equals(timestamp, other.timestamp);
	}

	@Override
	public String toString() {
		return "ApiError [timestamp=" + timestamp + ", status=" + status + ", error=" + error + ", message=" + message
				+ ", debugMessage=" + debugMessage + ", subErrors=" + subErrors + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
