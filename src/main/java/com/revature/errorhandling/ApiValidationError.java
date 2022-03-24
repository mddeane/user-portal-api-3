package com.revature.errorhandling;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false) 	// prevent an infinite loop that occurs in a bi-directional relationship
public class ApiValidationError extends ApiSubError {

	private String object; 			// name of the class of the object user tried to insert
	
	private String field; 			// field name that failed validation
		
	private Object rejectedValue;	// value that did not pass validation
	 
	private String reason;			// a message explaining why the object coudn't pass validation

	// constructors
	
	public ApiValidationError (String object, String reason) {
		this.object = object;
		this.reason = reason;
	}
	
	public ApiValidationError (String object, String field, String reason) {
		this(object, reason);
		this.field = field;
	}
	
	public ApiValidationError (String object, String field, String reason, Object rejectedValue) {
		this(object, field, reason);
		this.rejectedValue = rejectedValue;
	}
	
}
