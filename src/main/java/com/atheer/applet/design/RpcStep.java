package com.atheer.applet.design;

public class RpcStep extends Step {

	public RpcStep() {
		super();
		this.type = StepType.Rpc.toString();
	}

	public RpcStep(String id, String name, String label) {
		super(id, name, label);
		this.type = StepType.Rpc.toString();
	}

	
}
