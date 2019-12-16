package com.atheer.applet.design;

import java.util.ArrayList;
import java.util.List;

public class Wait extends Step {

	/*
	 * Wait until specified seconds passed.
	 */
	private int seconds;
	
	/*
	 * Wait until something happens. 
	 * System molecules play a role here in the background.
	 */
	private Condition condition;
	
	/*
	 * Engage the user with informative messages.
	 */
	private List<String> messages = new ArrayList<String>();
	
	public Wait() {
		super();
		this.type = StepType.Wait.toString();
	}

	public Wait(String id, String name, String label) {
		super(id, name, label);
		this.type = StepType.Wait.toString();
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	

}
