package com.atheer.applet.design;

import java.util.ArrayList;
import java.util.List;

public class Applet extends Model {

	private String description;
	private String category;
	private List<Lane> lanes = new ArrayList<Lane>();
	private List<String> audiences = new ArrayList<String>();
	
	public Applet() {
		super();
	}

	public Applet(String id, String name, String label) {
		super(id, name, label);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public List<Lane> getLanes() {
		return lanes;
	}

	public void setLanes(List<Lane> lanes) {
		this.lanes = lanes;
	}

	public List<String> getAudiences() {
		return audiences;
	}

	public void setAudiences(List<String> audiences) {
		this.audiences = audiences;
	}
	
	public Applet addLane(Lane lane) {
		this.lanes.add(lane);
		return this;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Applet other = (Applet) obj;
		return this.id.equals(other.id);
	}
	
	
}
