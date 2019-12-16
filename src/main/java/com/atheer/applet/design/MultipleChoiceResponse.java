package com.atheer.applet.design;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceResponse extends Response {
	
	private List<String> choices = new ArrayList<String>();
	private List<String> values = new ArrayList<String>();
	
	public MultipleChoiceResponse() {
		super();
		this.type = ResponseType.MultipleChoice.toString();
	}
	
	public MultipleChoiceResponse(String id, String name, String label) {
		super(id, name, label);
		this.type = ResponseType.MultipleChoice.toString();
	}

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}

