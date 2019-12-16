package com.atheer.applet.design;

public abstract class Response extends Model {

	protected String type;
	private Boolean required = Boolean.FALSE;
	private Condition preCondition;
	
	public Response() {
		super();
	}

	public Response(String id, String name, String label) {
		super(id, name, label);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public Condition getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(Condition preCondition) {
		this.preCondition = preCondition;
	}

}
