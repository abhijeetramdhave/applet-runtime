package com.atheer.applet.runtime.delivery;

import com.atheer.applet.runtime.AppletContext;
import com.atheer.applet.runtime.tracking.CompletionStatus;

public interface Molecule {
	
	public void init(AppletContext context);
	
	public void execute(AppletContext context);
	
	public void finish(AppletContext context);

	public String getId();
	
	public String getName();
	
	public String getLabel();

	public MoleculeType getType();
	
	public CompletionStatus getStatus();
	
	public Molecule getParent();
	
	public void setParent(Molecule molecule);

}
