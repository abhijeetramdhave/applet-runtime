package com.atheer.applet.runtime.tracking;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MoleculeTrackingData {
	
	private String id;
	private String moleculeId;
	private String name;
	private String type;
	private User actor;
	
	private Date startTime;
	private Date endTime;
	private CompletionStatus status = CompletionStatus.NotStarted;
	private Map<String, String> data = new HashMap<String, String>();
	
	public MoleculeTrackingData() {
		super();
	}

	public MoleculeTrackingData(String id, String moleculeId, String name, String type) {
		super();
		this.id = id;
		this.moleculeId = moleculeId;
		this.name = name;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMoleculeId() {
		return moleculeId;
	}

	public void setMoleculeId(String moleculeId) {
		this.moleculeId = moleculeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getActor() {
		return actor;
	}

	public void setActor(User actor) {
		this.actor = actor;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public CompletionStatus getStatus() {
		return status;
	}

	public void setStatus(CompletionStatus status) {
		this.status = status;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	
}
