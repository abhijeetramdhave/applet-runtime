package com.atheer.applet.design;

public class ConditionalSection extends Section {

	private Condition preCondition;
	
	public ConditionalSection() {
		super();
	}

	public ConditionalSection(String id, String name, String label, Condition condition) {
		super(id, name, label);
		this.preCondition = condition;
	}

	public Condition getPreCondition() {
		return preCondition;
	}

	public void setPreCondition(Condition preCondition) {
		this.preCondition = preCondition;
	}
	

}
