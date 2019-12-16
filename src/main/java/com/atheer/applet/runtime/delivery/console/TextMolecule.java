package com.atheer.applet.runtime.delivery.console;

public class TextMolecule extends BaseTextMolecule {

	private int maxLength;

	public TextMolecule() {
		super();
	}
	
	
	public TextMolecule(String id, String name, String label) {
		super(id, name, label);
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

}
