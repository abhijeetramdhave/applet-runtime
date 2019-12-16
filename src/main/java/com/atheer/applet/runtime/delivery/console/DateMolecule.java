package com.atheer.applet.runtime.delivery.console;

public class DateMolecule extends BaseTextMolecule {

	private String format = "dd/mm/yyyy";
	
	public DateMolecule() {
		super();
	}

	public DateMolecule(String id, String name, String label) {
		super(id, name, label);
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
		setHintText(format);
	}

	
}
