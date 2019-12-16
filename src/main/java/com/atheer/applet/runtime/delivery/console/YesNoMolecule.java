package com.atheer.applet.runtime.delivery.console;

public class YesNoMolecule extends BaseTextMolecule {

	public YesNoMolecule() {
		super();
	}

	public YesNoMolecule(String id, String name, String label) {
		super(id, name, label);
	}

	@Override
	protected void renderExtraInfo() {
		renderSpace(leftMargin);
		System.out.println("[Yes/No]");
	}

}
