package com.atheer.applet.runtime.delivery;

import java.util.List;

public interface CompositeMolecule extends Molecule {

	public void addChild(Molecule molecule);
	public List<Molecule> getChildren();
}
