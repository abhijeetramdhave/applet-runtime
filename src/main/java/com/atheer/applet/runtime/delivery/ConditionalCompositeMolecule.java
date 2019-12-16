package com.atheer.applet.runtime.delivery;

import java.util.Date;

import com.atheer.applet.design.Condition;
import com.atheer.applet.runtime.AppletContext;
import com.atheer.applet.runtime.Navigation;
import com.atheer.applet.runtime.tracking.CompletionStatus;
import com.atheer.applet.runtime.tracking.MoleculeTrackingData;

public class ConditionalCompositeMolecule extends BaseCompositeMolecule {

	private Condition condition;
	private boolean canExecute;
	
	public ConditionalCompositeMolecule() {
		super();
	}

	public ConditionalCompositeMolecule(String id, String name, String label, Condition condition) {
		super(id, name, label);
		this.condition = condition;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	@Override
	protected void doExecute(AppletContext context) {
		canExecute = context.evaluate(condition);

		if(!canExecute) {
			return;
		}
		
		for(Molecule child: children) {
			child.execute(context);
			if(context.getRequest().equals(Navigation.Exit)) {
				return;
			} 
		}
		
	}

	@Override
	public void finish(AppletContext context) {
		if(canExecute) {
			super.finish(context);
		} else {
			this.status = CompletionStatus.Skipped;
			context.setStatus(id, status);
			MoleculeTrackingData trackingData = context.getTrackingData(id);
			trackingData.setEndTime(new Date());
		}
		
	}
	
	
}
