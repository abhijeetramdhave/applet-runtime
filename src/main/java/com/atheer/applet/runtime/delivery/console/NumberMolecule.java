package com.atheer.applet.runtime.delivery.console;

public class NumberMolecule extends BaseTextMolecule {

	private Double minValue;
	private Double maxValue;
	
	public NumberMolecule() {
		super();
	}

	public NumberMolecule(String id, String name, String label) {
		super(id, name, label);
	}

	public Double getMinValue() {
		return minValue;
	}

	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	
}
