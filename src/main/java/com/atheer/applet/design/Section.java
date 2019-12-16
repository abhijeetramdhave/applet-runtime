package com.atheer.applet.design;

import java.util.ArrayList;
import java.util.List;

public class Section extends Model {
	
	private List<Step> steps = new ArrayList<Step>();
	
	public Section() {
		super();
	}

	public Section(String id, String name, String label) {
		super(id, name, label);
	}

	public List<Step> getSteps() {
		return steps;
	}

	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	
	public Section addStep(Step step) {
		this.steps.add(step);
		return this;
	}


}
