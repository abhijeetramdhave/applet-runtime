package com.atheer.applet.runtime.delivery;

import com.atheer.applet.design.Condition;

public class LoopMolecule extends ConditionalCompositeMolecule {
	
	private boolean preCondition = true;

	public LoopMolecule() {
		super();
	}

	public LoopMolecule(String id, String name, String label, Condition condition, boolean preCondition) {
		super(id, name, label, condition);
		this.preCondition = preCondition;
	}

	public boolean isPreCondition() {
		return preCondition;
	}

	public void setPreCondition(boolean preCondition) {
		this.preCondition = preCondition;
	}

	
}
