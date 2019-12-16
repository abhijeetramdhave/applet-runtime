package com.atheer.applet.runtime.delivery;

import java.util.Date;

import com.atheer.applet.runtime.AppletContext;
import com.atheer.applet.runtime.Navigation;
import com.atheer.applet.runtime.tracking.CompletionStatus;
import com.atheer.applet.runtime.tracking.MoleculeTrackingData;

public class BranchMolecule extends BaseCompositeMolecule {

	private boolean all = true;

	public BranchMolecule() {
		super();
	}

	public BranchMolecule(String id, String name, String label, boolean all) {
		super(id, name, label);
		this.all = all;
	}

	public boolean isAll() {
		return all;
	}

	public void setAll(boolean all) {
		this.all = all;
	}

	@Override
	protected void doExecute(AppletContext context) {
		for(Molecule child: children) {
			child.execute(context);
			
			if(context.getRequest().equals(Navigation.Exit)) {
				return;
			} 

			if(child.getStatus() == CompletionStatus.Completed && !all) {
				break;
			}
			
		}
		
	}

	@Override
	public void finish(AppletContext context) {
		this.status = CompletionStatus.InProgress;
		for(Molecule child: children) {
			if(child.getStatus() == CompletionStatus.Completed) {
				this.status = CompletionStatus.Completed;
				break;
			}
		}
		context.setStatus(id, status);
		MoleculeTrackingData trackingData = context.getTrackingData(id);
		trackingData.setEndTime(new Date());
	}
	
	
	
}
