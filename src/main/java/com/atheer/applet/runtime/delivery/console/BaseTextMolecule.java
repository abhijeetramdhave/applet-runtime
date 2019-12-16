package com.atheer.applet.runtime.delivery.console;

import com.atheer.applet.util.StringUtil;

public abstract class BaseTextMolecule extends UIMolecule {

	public BaseTextMolecule() {
		super();
	}

	public BaseTextMolecule(String id, String name, String label) {
		super(id, name, label);
	}

	@Override
	public void render() {
		renderLabel();
		renderExtraLabel();
		renderValue();
		renderExtraInfo();
		renderError();
	}

	protected void renderLabel() {
		renderSpace(leftMargin);
		String displayLabel = label + (this.required ? "*:" : ":");
		System.out.print(StringUtil.rpad(displayLabel, ' ', 50));
	}

	protected void renderExtraLabel() {
		
	}

	protected void renderValue() {
		String displayValue = StringUtil.lpad("", '_', 10);
		if(!StringUtil.isEmpty(this.value)) {
			displayValue = this.value;
		} else if(!StringUtil.isEmpty(this.hintText)) {
			displayValue = "Hint: " + this.hintText;
		}
		
		System.out.print(StringUtil.rpad(displayValue, ' ', 40));
		
		if(this.focus) {
			System.out.println("<=");
		} else {
			System.out.println();
		}
	}

	protected void renderExtraInfo() {
		
	}

	protected void renderError() {
		renderSpace(leftMargin);
		if(!StringUtil.isEmpty(this.errorText)) {
			System.out.println("Error: " + this.errorText);
		}
		renderBlankLines(2);
	}

}
