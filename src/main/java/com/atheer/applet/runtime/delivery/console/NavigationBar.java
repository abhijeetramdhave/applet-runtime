package com.atheer.applet.runtime.delivery.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.atheer.applet.runtime.AppletContext;
import com.atheer.applet.runtime.Navigation;

public class NavigationBar extends UIMolecule {

	private List<Button> buttons = new ArrayList<Button>();
	private int marginLeft = 5;
	private int marginRight = 0;
	
	private Button previous;
	private Button next;
	private Button exit;

	public NavigationBar() {
		previous = new Button("Previous", "p", 20);
		buttons.add(previous);
		
		next = new Button("Next", "n", 20);
		buttons.add(next);
		
		buttons.add(new Button("Content", "c", 20));
		buttons.add(new Button("Conversation", "o", 20));
		
		exit = new Button("Exit", "x", 20);
		buttons.add(exit);
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}

	@Override
	public void init(AppletContext context) {
		Map<Navigation, Boolean> navigations = context.getNavigations();
		
		previous.setEnabled(navigations.get(Navigation.Previous));
		next.setEnabled(navigations.get(Navigation.Next));
		exit.setEnabled(navigations.get(Navigation.Exit));
	}

	@Override
	public void render() {
		renderLine(getWidth());
		renderSpace(marginLeft);
		for(Button button: buttons) {
			button.render();
		}
		renderBlankLines(2);
		renderLine(getWidth());
	}
	
	private int getWidth() {
		int width = marginLeft + marginRight;
		for(Button button: buttons) {
			width += button.getWidth();
		}
		return width;
	}
	
	public void addButton(Button button) {
		this.buttons.add(button);
	}
	
	public List<String> getCommands() {
		List<String> commands = new ArrayList<String>();
		for(Button button: buttons) {
			if(button.isEnabled()) {
				commands.add(button.getShortCut());
			}
		}
		return commands;
	}

}
