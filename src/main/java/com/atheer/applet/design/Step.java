package com.atheer.applet.design;

public class Step extends Model {

	protected String type;

	public Step() {
		super();
	}

	public Step(String id, String name, String label) {
		super(id, name, label);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
