package com.atheer.applet.design;

import java.util.ArrayList;
import java.util.List;

public class Branch extends Step {
	private BranchMode mode = BranchMode.All;
	
	private List<ConditionalSection> paths = new ArrayList<ConditionalSection>();
	private Section defaultPath;

	public Branch() {
		super();
	}

	public Branch(String id, String name, String label) {
		super(id, name, label);
	}

	public BranchMode getMode() {
		return mode;
	}

	public void setMode(BranchMode mode) {
		this.mode = mode;
	}

	public List<ConditionalSection> getPaths() {
		return paths;
	}

	public void setPaths(List<ConditionalSection> paths) {
		this.paths = paths;
	}

	public Section getDefaultPath() {
		return defaultPath;
	}

	public void setDefaultPath(Section defaultPath) {
		this.defaultPath = defaultPath;
	}

	
}
