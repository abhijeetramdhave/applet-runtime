package com.atheer.applet.runtime.delivery.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.atheer.applet.runtime.AppletContext;
import com.atheer.applet.runtime.delivery.validator.RangeValidator;
import com.atheer.applet.runtime.delivery.validator.RequiredValidator;
import com.atheer.applet.runtime.delivery.validator.ValidationException;
import com.atheer.applet.runtime.tracking.CompletionStatus;
import com.atheer.applet.util.StringUtil;

public class NavigableUIMolecule extends UIMolecule {

	protected String appletLabel;
	protected String sectionLabel;
	protected String description;
	
	protected NavigationBar navBar;
	protected List<UIMolecule> children = new ArrayList<UIMolecule>();
	
	protected int width = 105;
	protected int marginLeft = 5;
	protected int current = 0;
	
	public NavigableUIMolecule(String id, String name, String label,
			String appletLabel, String sectionLabel, String description) {
		super(id, name, label);
		this.appletLabel = appletLabel;
		this.sectionLabel = sectionLabel;
		this.description = description;
		this.navBar = new NavigationBar();
	}

	public String getAppletLabel() {
		return appletLabel;
	}

	public void setAppletLabel(String appletLabel) {
		this.appletLabel = appletLabel;
	}

	public String getSectionLabel() {
		return sectionLabel;
	}

	public void setSectionLabel(String sectionLabel) {
		this.sectionLabel = sectionLabel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public NavigationBar getNavBar() {
		return navBar;
	}

	public void setNavBar(NavigationBar navBar) {
		this.navBar = navBar;
	}

	public List<UIMolecule> getChildren() {
		return children;
	}

	public void setChildren(List<UIMolecule> children) {
		this.children = children;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getMarginLeft() {
		return marginLeft;
	}

	public void setMarginLeft(int marginLeft) {
		this.marginLeft = marginLeft;
	}
	
	public void addChild(UIMolecule control) {
		if(this.children.isEmpty()) {
			control.setFocus(true);
		}
		this.children.add(control);
		addValidators(control);
	}
	
	protected void addValidators(UIMolecule control) {
		if(control.isRequired()) {
			control.addValidator(new RequiredValidator());
		}
		
		if(control instanceof NumberMolecule) {
			NumberMolecule molecule = (NumberMolecule) control;
			control.addValidator(new RangeValidator(molecule.getMinValue(), molecule.getMaxValue()));
		}
	}

	@Override
	public void render() {
		renderBlankLines(25);
		renderHeaders();
		navBar.render();
		renderDescription();
		renderControls();
		getInput();
	}
	
	@Override
	public void init(AppletContext context) {
		super.init(context);
		navBar.init(context);
		initData(context);
	}

	protected void initData(AppletContext context) {
		Map<String, String> data = context.getData(this.id);

		for(UIMolecule child: children) {
			child.setValue(data.get(child.getName()));
		}
	}

	@Override
	public void finish(AppletContext context) throws ValidationException {
		validate();
		this.status = CompletionStatus.Completed;
		context.setStatus(this.id, this.status);
		
		Map<String, String> data = new HashMap<String, String>();
		for(UIMolecule child: children) {
			data.put(child.getName(), child.getValue());
		}
		context.setData(this.id, data);
		super.finish(context);
	}

	@Override
	public void validate() throws ValidationException {
		for(UIMolecule child: children) {
			try {
				child.validate();
			} catch (ValidationException ve) {
				setFocus(child);
				throw ve;
			}
		}
	}

	protected void renderHeaders() {
		renderLine(getWidth());
		System.out.println(StringUtil.pad(appletLabel, ' ', getWidth()));
		System.out.println(StringUtil.pad(sectionLabel, ' ', getWidth()));
	}

	protected void renderDescription() {
		renderSpace(marginLeft);
		System.out.println(StringUtil.wrap(description, getWidth()));
		System.out.println();
	}

	protected void renderControls() {
		for(UIMolecule control: children) {
			control.render();
		}
		renderLine(getWidth());
	}

	protected void getInput() {
		renderSpace(marginLeft);
		renderCursor();
		Scanner in = new Scanner(System.in);
		String input = handleBlank(in.nextLine());
		boolean stay = processInput(input);
		if(stay) {
			System.out.println("Staying back...");
			render();
		}
	}

	protected void renderCursor() {
		System.out.print("=> ");
	}

	protected String handleBlank(String input) {
		if(StringUtil.isEmpty(input)) {
			return "+";
		}
		return input;
	}	
	
	protected boolean processInput(String input) {
		if(isCommand(input)) {
			return processCommand(input);
		} else if(children != null && !children.isEmpty()) {
			System.out.println("Processing input...");
			children.get(current).setValue(input);
			children.get(current).setErrorText("");
			processCommand("+");
		}
		return true;
	}

	protected boolean isCommand(String input) {
		List<String> commands = navBar.getCommands();
		commands.add("-");
		commands.add("+");

		return commands.contains(input);
	}
	
	protected boolean processCommand(String input) {
		System.out.println("Processing command " + input);
		switch (input) {
		case "+": 
			current = Math.min(current + 1, children.size() - 1);
			changeFocus();
			return true;
		case "-":
			current = Math.max(current -1, 0);
			changeFocus();
			return true;
		case "p":
			context.previous();
			return false;
		case "n":
			context.next();
			return false;
		case "x":
			context.exit();
		}
		return false;
		
	}
	
	protected void changeFocus() {
		for(int i = 0; i < children.size(); ++i) {
			children.get(i).setFocus(i == current);
		}
	}

	protected void setFocus(UIMolecule control) {
		for(int i = 0; i < children.size(); ++i) {
			if(control.equals(children.get(i))) {
				children.get(i).setFocus(true);
				current = i;
			} else {
				children.get(i).setFocus(false);
			}
			
		}
		
	}
}
