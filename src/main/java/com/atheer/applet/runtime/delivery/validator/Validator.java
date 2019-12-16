package com.atheer.applet.runtime.delivery.validator;

public interface Validator {
	
	public void validate(String name, String value) throws ValidationException;

}
