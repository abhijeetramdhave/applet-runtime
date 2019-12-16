package com.atheer.applet.design;

public class Model {

	protected String id;
	protected String name;
	protected String label;
	
	public Model() {
		super();
	}

	public Model(String id, String name, String label) {
		super();
		this.id = id;
		this.name = name;
		this.label = label;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof Model) {
			Model other = (Model) obj;
			return this.id.equals(other.id);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Model [id=" + id + ", name=" + name + ", label=" + label + "]";
	}

	
}
