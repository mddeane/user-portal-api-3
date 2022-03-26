package com.revature.errorhandling;

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

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getRejectedValue() {
		return rejectedValue;
	}

	public void setRejectedValue(Object rejectedValue) {
		this.rejectedValue = rejectedValue;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "ApiValidationError [object=" + object + ", field=" + field + ", rejectedValue=" + rejectedValue
				+ ", reason=" + reason + "]";
	}
	
	
	
}
