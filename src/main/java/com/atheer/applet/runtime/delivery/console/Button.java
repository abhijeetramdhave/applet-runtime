package com.atheer.applet.runtime.delivery.console;

import com.atheer.applet.util.StringUtil;

public class Button extends UIMolecule {

	private String label;
	private String shortCut;
	private int width;
	private boolean enabled = true;
	
	public Button(String label, String shortCut, int width) {
		super();
		this.label = label;
		this.shortCut = shortCut;
		this.width = width;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getShortCut() {
		return shortCut;
	}

	public void setShortCut(String shortCut) {
		this.shortCut = shortCut;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public void render() {
		String displayLabel = label;
		if(enabled) {
			displayLabel += " (" + shortCut + ")";
		}
		System.out.print(StringUtil.rpad(displayLabel, ' ', width));
	}

}
