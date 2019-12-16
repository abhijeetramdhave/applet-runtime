package com.atheer.applet.design;

public class Condition {
	
	private String operand; // step.property
	private Operator operator;
	private String value;
	
	public Condition() {
		super();
	}

	public Condition(String operand, Operator operator, String value) {
		super();
		this.operand = operand;
		this.operator = operator;
		this.value = value;
	}

	public String getOperand() {
		return operand;
	}

	public void setOperand(String operand) {
		this.operand = operand;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	

}
