package com.atheer.applet.runtime.delivery;

import java.util.Date;

import com.atheer.applet.runtime.AppletContext;
import com.atheer.applet.runtime.delivery.validator.ValidationException;
import com.atheer.applet.runtime.tracking.CompletionStatus;
import com.atheer.applet.runtime.tracking.MoleculeTrackingData;

public class BaseMolecule implements Molecule {

	protected String id;
	protected String name;
	protected String label;
	protected MoleculeType type = MoleculeType.Step;
	protected CompletionStatus status = CompletionStatus.NotStarted;
	protected Molecule parent;
	
	protected AppletContext context;

	public BaseMolecule() {
		super();
	}

	public BaseMolecule(String id, String name, String label) {
		super();
		this.id = id;
		this.name = name;
		this.label = label;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public MoleculeType getType() {
		return type;
	}

	@Override
	public CompletionStatus getStatus() {
		return status;
	}

	@Override
	public Molecule getParent() {
		return parent;
	}

	@Override
	public void setParent(Molecule parent) {
		this.parent = parent;
	}

	@Override
	public void execute(AppletContext context) {
		init(context);
		
		boolean success = false;
		while(!success) {
			System.out.println("Executing " + this.name);
			doExecute(context);
			try {
				finish(context);
				success = true;
				System.out.println("Finish returned successfully...");
			} catch(ValidationException ve) {
				System.out.println("Validation exception: " + ve.getMessage());
				success = false;
			}
		}
	}
	
	@Override
	public void init(AppletContext context) {
		context.setCurrent(this);
		this.context = context;

		status = context.getStatus(id);
		if(status != CompletionStatus.Completed) {
			status = CompletionStatus.InProgress;
		}
		
		MoleculeTrackingData trackingData = context.getTrackingData(id);
		trackingData.setActor(context.getUser());
		trackingData.setStatus(status);
		trackingData.setStartTime(new Date());
		
	}

	@Override
	public void finish(AppletContext context) throws ValidationException {
		MoleculeTrackingData trackingData = context.getTrackingData(id);
		trackingData.setEndTime(new Date());
	}
	
	protected void doExecute(AppletContext context) {
		
	}

}
