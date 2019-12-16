package com.atheer.applet.runtime.tracking;

public class User {
	private String id;
	private String name;
	
	public User() {
		super();
	}
	
	public User(String id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof User) {
			User other = (User) obj;
			return this.id.equals(other.id);
		}
		return false;
	}

	
}
