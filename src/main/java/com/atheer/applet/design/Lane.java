package com.atheer.applet.design;

import java.util.ArrayList;
import java.util.List;

public class Lane extends Model {
	
	private List<String> audiences = new ArrayList<String>();
	private List<Section> sections = new ArrayList<Section>();
	
	public Lane() {
		super();
	}

	public Lane(String id, String name, String label) {
		super(id, name, label);
	}

	public List<String> getAudiences() {
		return audiences;
	}

	public void setAudiences(List<String> audiences) {
		this.audiences = audiences;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
	
	public Lane addSection(Section section) {
		this.sections.add(section);
		return this;
	}

	
}
