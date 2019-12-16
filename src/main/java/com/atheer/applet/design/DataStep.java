package com.atheer.applet.design;

import java.util.ArrayList;
import java.util.List;

public class DataStep extends Step {
	
	private String icon;
	private String description;
	private List<String> attachments = new ArrayList<String>();
	private List<Response> responses = new ArrayList<Response>();
	
	public DataStep() {
		super();
		this.type = StepType.Data.toString();
	}

	public DataStep(String id, String name, String label) {
		super(id, name, label);
		this.type = StepType.Data.toString();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	public List<Response> getResponses() {
		return responses;
	}

	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
	
	public DataStep addResponse(Response response) {
		this.responses.add(response);
		return this;
	}
}
