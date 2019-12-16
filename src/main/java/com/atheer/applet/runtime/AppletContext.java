package com.atheer.applet.runtime;

import java.util.Map;

import com.atheer.applet.design.Condition;
import com.atheer.applet.runtime.delivery.Molecule;
import com.atheer.applet.runtime.tracking.CompletionStatus;
import com.atheer.applet.runtime.tracking.MoleculeTrackingData;
import com.atheer.applet.runtime.tracking.TrackingData;
import com.atheer.applet.runtime.tracking.User;

public interface AppletContext {
	
	// Navigation commands
	public void setCurrent(Molecule molecule);
	
	public void previous();
	
	public void next();
	
	public void exit();

	public Navigation getRequest();
	
	public Map<Navigation, Boolean> getNavigations();
	
	// Data commands
	public User getUser();
	
	public TrackingData getTrackingData();
	
	public MoleculeTrackingData getTrackingData(String moleculeId);
	
	public Map<String, String> getData(String moleculeId);
	
	public void setData(String moleculeId, Map<String, String> data);
	
	public CompletionStatus getStatus(String moleculeId);
	
	public void setStatus(String moleculeId, CompletionStatus status);
	
	// Processing commands
	public boolean evaluate(Condition condition);
}
