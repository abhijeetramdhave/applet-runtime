package com.atheer.applet.runtime.delivery.console;

import java.util.List;

import com.atheer.applet.design.Condition;
import com.atheer.applet.runtime.AppletContext;
import com.atheer.applet.runtime.delivery.validator.ValidationException;
import com.atheer.applet.runtime.tracking.CompletionStatus;
import com.atheer.applet.util.StringUtil;

public class WaitMolecule extends NavigableUIMolecule {

	private int seconds;
	private int counter;
	private Condition condition;
	private List<String> messages; 
	private int messageIndex = 0;

	public WaitMolecule(String id, String name, String label, 
			String appletLabel, String sectionLabel, String description) {
		super(id, name, label, appletLabel, sectionLabel, description);
	}

	@Override
	public void render() {
		if(counter >= 0) {
			renderBlankLines(25);
			renderHeaders();
			navBar.render();
			renderBlankLines(5);
			renderMessage();
			renderTime();
			renderLine(getWidth());
			try {
				Thread.sleep(1000);
			} catch(Throwable t) {
				
			}
			counter--;
			render();
		}
		context.next();
	}

	private void renderMessage() {
		if(messages != null && !messages.isEmpty()) {
			System.out.println(StringUtil.pad(messages.get(messageIndex), ' ', getWidth()));
			messageIndex = (messageIndex + 1) % messages.size();
		}
	}

	private void renderTime() {
		renderBlankLines(2);
		System.out.println(StringUtil.pad(StringUtil.lpad("" + counter, '0', 3), ' ', getWidth()));
		renderBlankLines(5);
	}

	@Override
	public void init(AppletContext context) {
		super.init(context);
		counter = seconds;
	}

	@Override
	public void finish(AppletContext context) throws ValidationException {
		context.setStatus(id, CompletionStatus.Completed);
		super.finish(context);
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
