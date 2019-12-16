package com.atheer.applet.design;

public class NumberResponse extends Response {

	private Double value;
	private Double minValue;
	private Double maxValue;
	
	public NumberResponse() {
		super();
		this.type = ResponseType.Number.toString();
	}

	public NumberResponse(String id, String name, String label) {
		super(id, name, label);
		this.type = ResponseType.Number.toString();
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
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
