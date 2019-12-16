package com.atheer.applet.runtime.delivery.validator;

import com.atheer.applet.util.StringUtil;

public class RequiredValidator implements Validator {

	@Override
	public void validate(String name, String value) throws ValidationException {
		if(StringUtil.isEmpty(value)) {
			throw new ValidationException(name + " is required field.");
		}
		
	}

}
