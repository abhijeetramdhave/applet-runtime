package com.atheer.applet.runtime.delivery.console;

import java.util.ArrayList;
import java.util.List;

import com.atheer.applet.runtime.AppletContext;
import com.atheer.applet.runtime.delivery.BaseMolecule;
import com.atheer.applet.runtime.delivery.validator.ValidationException;
import com.atheer.applet.runtime.delivery.validator.Validator;
import com.atheer.applet.util.StringUtil;

public abstract class UIMolecule extends BaseMolecule {

	protected boolean focus;

	protected String value;
	protected boolean required;
	protected String hintText;
	protected String errorText;
	
	protected int leftMargin = 5;

	protected List<Validator> validators = new ArrayList<Validator>();
	
	public UIMolecule() {
		super();
	}

	public UIMolecule(String id, String name, String label) {
		super(id, name, label);
	}

	@Override
	protected void doExecute(AppletContext context) {
		render();
	}

	public abstract void render();

	public boolean isFocus() {
		return focus;
	}

	public void setFocus(boolean focus) {
		this.focus = focus;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getHintText() {
		return hintText;
	}

	public void setHintText(String hintText) {
		this.hintText = hintText;
	}

	public String getErrorText() {
		return errorText;
	}

	public void setErrorText(String errorText) {
		this.errorText = errorText;
	}
	
	public void addValidator(Validator validator) {
		this.validators.add(validator);
	}
	
	public void validate() throws ValidationException {
		try {
			for(Validator validator: this.validators) {
				validator.validate(label, value);
				errorText = "";
			}
		} catch(ValidationException ve) {
			errorText = ve.getMessage();
			throw ve;
		} 
	}
	
	protected void renderBlankLines(int number) {
		for(int i = 1; i < number; ++i) {
			System.out.println();
		}
	}
	
	protected void renderSpace(int number) {
		System.out.print(StringUtil.lpad("", ' ', number));
	}
	
	protected void renderLine(int width) {
		System.out.println(StringUtil.lpad("", '_', width));
		System.out.println();
	}
}
