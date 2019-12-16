package com.atheer.applet.design;

public class TextResponse extends Response {

	private String value;
	private int maxLength;
	private String hintText;

	public TextResponse() {
		super();
		this.type = ResponseType.Text.toString();
	}
	
	public TextResponse(String id, String name, String label) {
		super(id, name, label);
		this.type = ResponseType.Text.toString();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public String getHintText() {
		return hintText;
	}

	public void setHintText(String hintText) {
		this.hintText = hintText;
	}

	
}
