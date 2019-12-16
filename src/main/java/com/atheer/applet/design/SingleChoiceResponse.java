package com.atheer.applet.design;

import java.util.ArrayList;
import java.util.List;

public class SingleChoiceResponse extends Response {

	private List<String> choices = new ArrayList<String>();
	private String value;
	
	public SingleChoiceResponse() {
		super();
		this.type = ResponseType.SingleChoice.toString();
	}

	public SingleChoiceResponse(String id, String name, String label) {
		super(id, name, label);
		this.type = ResponseType.SingleChoice.toString();
	}

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
