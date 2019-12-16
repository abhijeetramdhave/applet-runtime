package com.atheer.applet.runtime.delivery.console;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceMolecule extends BaseTextMolecule {

	protected List<String> choices = new ArrayList<String>();
	
	public MultipleChoiceMolecule() {
		super();
	}

	public MultipleChoiceMolecule(String id, String name, String label) {
		super(id, name, label);
	}

	public List<String> getChoices() {
		return choices;
	}

	public void setChoices(List<String> choices) {
		this.choices = choices;
	}

	@Override
	protected void renderExtraInfo() {
		for(int i = 1; i <= choices.size(); ++i) {
			renderSpace(2 * leftMargin);
			System.out.println(i + ". " + choices.get(i-1));
		}
	}

	
}
