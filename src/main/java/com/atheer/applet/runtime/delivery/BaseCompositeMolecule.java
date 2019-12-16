package com.atheer.applet.runtime.delivery;

import java.util.ArrayList;
import java.util.List;

import com.atheer.applet.runtime.AppletContext;
import com.atheer.applet.runtime.Navigation;
import com.atheer.applet.runtime.tracking.CompletionStatus;

public class BaseCompositeMolecule extends BaseMolecule implements CompositeMolecule {

	protected List<Molecule> children = new ArrayList<Molecule>();
	
	public BaseCompositeMolecule() {
		super();
	}

	public BaseCompositeMolecule(String id, String name, String label) {
		super(id, name, label);
	}

	@Override
	public void addChild(Molecule molecule) {
		children.add(molecule);
		molecule.setParent(this);
	}

	@Override
	public List<Molecule> getChildren() {
		return children;
	}
	
	@Override
	protected void doExecute(AppletContext context) {
		for(Molecule child: children) {
			child.execute(context);
			if(context.getRequest().equals(Navigation.Exit)) {
				return;
			} 
		}
		
	}

	@Override
	public void finish(AppletContext context) {
		this.status = CompletionStatus.Completed;
		for(Molecule child: children) {
			if(child.getStatus() != CompletionStatus.Completed) {
				this.status = CompletionStatus.InProgress;
				break;
			}
		}
		context.setStatus(id, status);
		super.finish(context);
	}
	

}
