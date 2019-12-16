package com.atheer.applet.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atheer.applet.design.Applet;
import com.atheer.applet.design.Condition;
import com.atheer.applet.runtime.delivery.Device;
import com.atheer.applet.runtime.delivery.Molecule;
import com.atheer.applet.runtime.delivery.console.UIMolecule;
import com.atheer.applet.runtime.expressions.SimpleConditionResolver;
import com.atheer.applet.runtime.tracking.CompletionStatus;
import com.atheer.applet.runtime.tracking.MoleculeTrackingData;
import com.atheer.applet.runtime.tracking.TrackingData;
import com.atheer.applet.runtime.tracking.TrackingService;
import com.atheer.applet.runtime.tracking.User;

public class AppletRuntime implements AppletContext {
	
	private User user;
	private Molecule current;
	private List<Molecule> visited = new ArrayList<Molecule>();
	private int visitedIndex = 0;
	
	private Navigation request;
	
	private TrackingService trackingService = new TrackingService();
	private TrackingData trackingData;
	
	public void launch(Applet applet, User user) {
		this.user = user;
		MoleculeFactory factory = MoleculeFactory.getInstance(Device.Console);
		Molecule molecule = factory.getAppletMolecule(applet);
		trackingData = trackingService.createTrackingData(molecule, user);
		trackingService.saveTrackingData(this.trackingData);
		molecule.execute(this);
		trackingService.saveTrackingData(this.trackingData);
	}
	
	public void launch(Applet applet, User user, TrackingData trackingData) {
		this.user = user;
		MoleculeFactory factory = MoleculeFactory.getInstance(Device.Console);
		Molecule molecule = factory.getAppletMolecule(applet);
		this.trackingData = trackingData;
		molecule.execute(this);
		trackingService.saveTrackingData(this.trackingData);
	}

	@Override
	public void setCurrent(Molecule molecule) {
		this.current = molecule; 
		if(isReVisitable(molecule)) {
			if(!visited.contains(molecule)) {
				visited.add(molecule);
			}
			visitedIndex = visited.indexOf(molecule);
		}
	}
	
	private boolean isReVisitable(Molecule molecule) {
		return (molecule instanceof UIMolecule);
	}

	@Override
	public void previous() {
		this.request = Navigation.Previous;
		if(visitedIndex > 0) {
			current.finish(this);
			Molecule previous = visited.get(--visitedIndex);
			previous.execute(this);
		}
	}

	@Override
	public void next() {
		this.request = Navigation.Next;
		if(visitedIndex < visited.size() - 1) {
			current.finish(this);
			Molecule next = visited.get(++visitedIndex);
			next.execute(this);
		}
	}

	@Override
	public void exit() {
		this.request = Navigation.Exit;
	}

	public Navigation getRequest() {
		return request;
	}

	@Override
	public Map<Navigation, Boolean> getNavigations() {
		Map<Navigation, Boolean> navigations = new HashMap<Navigation, Boolean>();
		navigations.put(Navigation.Previous, visitedIndex > 0);
		navigations.put(Navigation.Next, Boolean.TRUE);
		navigations.put(Navigation.Exit, Boolean.TRUE);
		return navigations;
	}

	@Override
	public User getUser() {
		return this.user;
	}

	public TrackingData getTrackingData() {
		return trackingData;
	}

	@Override
	public MoleculeTrackingData getTrackingData(String moleculeId) {
		return trackingData.getData(moleculeId);
	}

	@Override
	public Map<String, String> getData(String moleculeId) {
		MoleculeTrackingData moleculeData = trackingData.getData(moleculeId);
		if(moleculeData != null) {
			return moleculeData.getData();
		}
		return new HashMap<String, String>();
	}

	@Override
	public void setData(String moleculeId, Map<String, String> data) {
		MoleculeTrackingData moleculeData = trackingData.getData(moleculeId);
		if(moleculeData != null) {
			moleculeData.setData(data);
		}
	}

	@Override
	public CompletionStatus getStatus(String moleculeId) {
		MoleculeTrackingData moleculeData = trackingData.getData(moleculeId);
		if(moleculeData != null) {
			return moleculeData.getStatus();
		}
		return CompletionStatus.NotStarted;
	}

	@Override
	public void setStatus(String moleculeId, CompletionStatus status) {
		MoleculeTrackingData moleculeData = trackingData.getData(moleculeId);
		if(moleculeData != null) {
			moleculeData.setStatus(status);
		}
	}

	@Override
	public boolean evaluate(Condition condition) {
		return new SimpleConditionResolver(this).resolve(condition);
	}

}
