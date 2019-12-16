package com.atheer.applet.runtime.delivery.validator;

import com.atheer.applet.util.StringUtil;

public class RangeValidator implements Validator {

	private Double minValue;
	private Double maxValue;
	
	public RangeValidator(Double minValue, Double maxValue) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	@Override
	public void validate(String name, String value) throws ValidationException {
		if(StringUtil.isEmpty(value)) {
			return;
		}
		
		Double doubleValue = 0.0;
		try {
			doubleValue = Double.valueOf(value);
		} catch (Exception ex) {
			throw new ValidationException("name" + " value must be numeric.");
		}
		
		if((minValue != null && doubleValue < minValue) ||
				(maxValue != null && doubleValue > maxValue)) {
			StringBuilder message = new StringBuilder();
			message.append(name).append(" value out of range. ");
			
			if(minValue != null && maxValue != null) {
				message.append("Specify between " + minValue + " and " + maxValue + ".");
			} else if (minValue != null) {
				message.append("Specify above " + minValue + ".");
			} else {
				message.append("Specify below " + maxValue + ".");
			}
			
			throw new ValidationException(message.toString());
		}
		
	}

}
