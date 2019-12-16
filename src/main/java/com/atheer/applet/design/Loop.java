package com.atheer.applet.design;

public class Loop extends Step {

	private Condition preCondition;
	private Condition postCondition;
	private Section section;
	
	public Loop() {
		super();
		this.type = StepType.Loop.toString();
	}


	public Loop(String id, String name, String label, Condition condition, boolean isPre) {
		super(id, name, label);
		this.type = StepType.Loop.toString();
		if(isPre) {
			this.preCondition = condition;
		} else {
			this.postCondition = condition;
		}
	}


	public Condition getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(Condition preCondition) {
		this.preCondition = preCondition;
	}

	public Condition getPostCondition() {
		return postCondition;
	}

	public void setPostCondition(Condition postCondition) {
		this.postCondition = postCondition;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	
}
