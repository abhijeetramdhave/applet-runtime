package com.atheer.applet.design;

public class YesNoResponse extends Response {

	private Boolean value;
	private Boolean defaultValue;
	
	public YesNoResponse() {
		super();
		this.type = ResponseType.YesNo.toString();
	}

	public YesNoResponse(String id, String name, String label) {
		super(id, name, label);
		this.type = ResponseType.YesNo.toString();
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	public Boolean getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Boolean defaultValue) {
		this.defaultValue = defaultValue;
	}
	
}
