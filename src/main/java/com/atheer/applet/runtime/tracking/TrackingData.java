package com.atheer.applet.runtime.tracking;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrackingData {
	private String id;
	private String appletId;
	private List<User> owners;
	private Date startTime;
	private Date endTime;
	private CompletionStatus status = CompletionStatus.NotStarted;

	private List<MoleculeTrackingData> moleculeData = new ArrayList<MoleculeTrackingData>();
	
	private transient Map<String, MoleculeTrackingData> idMap = new HashMap<String, MoleculeTrackingData>();
	private transient Map<String, MoleculeTrackingData> nameMap = new HashMap<String, MoleculeTrackingData>();
	

	public TrackingData() {
		super();
	}

	public TrackingData(String id, String appletId, List<User> owners) {
		super();
		this.id = id;
		this.appletId = appletId;
		this.owners = owners;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAppletId() {
		return appletId;
	}

	public void setAppletId(String appletId) {
		this.appletId = appletId;
	}

	public List<User> getOwners() {
		return owners;
	}

	public void setOwners(List<User> owners) {
		this.owners = owners;
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

	public List<MoleculeTrackingData> getMoleculeData() {
		return moleculeData;
	}

	public void setMoleculeData(List<MoleculeTrackingData> moleculeData) {
		this.moleculeData = moleculeData;
	}
	
	public MoleculeTrackingData getData(String id) {
		populateMap();
		return idMap.get(id);
	}
	
	public Map<String, MoleculeTrackingData> getIdMap() {
		return idMap;
	}

	public Map<String, MoleculeTrackingData> getNameMap() {
		return nameMap;
	}

	public void populateMap() {
		if(idMap.isEmpty()) {
			for(MoleculeTrackingData data: moleculeData) {
				idMap.put(data.getMoleculeId(), data);
			}
		}
		
		if(nameMap.isEmpty()) {
			for(MoleculeTrackingData data: moleculeData) {
				nameMap.put(data.getName(), data);
			}
		}
	}
}
