package com.atheer.applet.runtime;

import com.atheer.applet.design.Applet;
import com.atheer.applet.design.Branch;
import com.atheer.applet.design.BranchMode;
import com.atheer.applet.design.Condition;
import com.atheer.applet.design.ConditionalSection;
import com.atheer.applet.design.DataStep;
import com.atheer.applet.design.DateResponse;
import com.atheer.applet.design.Lane;
import com.atheer.applet.design.Loop;
import com.atheer.applet.design.MultipleChoiceResponse;
import com.atheer.applet.design.NumberResponse;
import com.atheer.applet.design.Response;
import com.atheer.applet.design.RpcStep;
import com.atheer.applet.design.Section;
import com.atheer.applet.design.SingleChoiceResponse;
import com.atheer.applet.design.Step;
import com.atheer.applet.design.TextResponse;
import com.atheer.applet.design.Wait;
import com.atheer.applet.design.YesNoResponse;
import com.atheer.applet.runtime.delivery.BaseCompositeMolecule;
import com.atheer.applet.runtime.delivery.BranchMolecule;
import com.atheer.applet.runtime.delivery.ConditionalCompositeMolecule;
import com.atheer.applet.runtime.delivery.Device;
import com.atheer.applet.runtime.delivery.LoopMolecule;
import com.atheer.applet.runtime.delivery.Molecule;
import com.atheer.applet.runtime.delivery.console.ConsoleMoleculeFactory;

public abstract class MoleculeFactory {
	
	protected String appletLabel;
	protected String sectionLabel;
	
	public static MoleculeFactory getInstance(Device device) {
		if(device == Device.Console) {
			return new ConsoleMoleculeFactory();
		}
		// Just one implementation for proof of concept.
		return new ConsoleMoleculeFactory();
	}
	
	public String getAppletLabel() {
		return appletLabel;
	}

	public void setAppletLabel(String appletLabel) {
		this.appletLabel = appletLabel;
	}

	public String getSectionLabel() {
		return sectionLabel;
	}

	public void setSectionLabel(String sectionLabel) {
		this.sectionLabel = sectionLabel;
	}

	public Molecule getAppletMolecule(Applet applet) {
		setAppletLabel(applet.getLabel());
		BaseCompositeMolecule appletMolecule = new BaseCompositeMolecule(applet.getId(), applet.getName(), applet.getLabel());
		for(Lane lane: applet.getLanes()) {
			appletMolecule.addChild(getLaneMolecule(lane));
		}
		return appletMolecule;
	}

	public Molecule getLaneMolecule(Lane lane) {
		BaseCompositeMolecule laneMolecule = new BaseCompositeMolecule(lane.getId(), lane.getName(), lane.getLabel());
		for(Section section: lane.getSections()) {
			laneMolecule.addChild(getSectionMolecule(section));
		}
		return laneMolecule;
	}

	public Molecule getSectionMolecule(Section section) {
		BaseCompositeMolecule sectionMolecule = new BaseCompositeMolecule(section.getId(), section.getName(), section.getLabel());
		for(Step step: section.getSteps()) {
			setSectionLabel(section.getLabel());
			sectionMolecule.addChild(getStepMolecule(step));
		}
		return sectionMolecule;
	}

	public Molecule getStepMolecule(Step step) {
		if(step instanceof DataStep) {
			return getDataStepMolecule((DataStep) step);
		} else if(step instanceof RpcStep) {
			return getRpcMolecule((RpcStep) step);
		} if(step instanceof Wait) {
			return getWaitMolecule((Wait) step);
		} if(step instanceof Branch) {
			return getBranchMolecule((Branch) step);
		} if(step instanceof Loop) {
			return getLoopMolecule((Loop) step);
		} 
		return null;
	}
	
	public Molecule getBranchMolecule(Branch branch) {
		BranchMolecule molecule = new BranchMolecule(
				branch.getId(), branch.getName(), branch.getLabel(), 
				branch.getMode().equals(BranchMode.All));
				
		for(ConditionalSection section: branch.getPaths()) {
			molecule.addChild(getConditionalSectionMolecule(section));
		}
		if(branch.getDefaultPath() != null) {
			molecule.addChild(getSectionMolecule(branch.getDefaultPath()));
		}
		return molecule;
	}

	public Molecule getConditionalSectionMolecule(ConditionalSection section) {
		setSectionLabel(section.getLabel());
		ConditionalCompositeMolecule molecule = new ConditionalCompositeMolecule(
				section.getId(), section.getName(), section.getLabel(), section.getPreCondition());
		
		for(Step step: section.getSteps()) {
			molecule.addChild(getStepMolecule(step));
		}
		return molecule;
	}
	
	public Molecule getLoopMolecule(Loop loop) {
		setSectionLabel(loop.getLabel());
		Condition condition = loop.getPreCondition();
		boolean preCondition = true;
		if(condition == null) {
			condition = loop.getPostCondition();
			preCondition = false;
		}
		
		LoopMolecule molecule = new LoopMolecule(
				loop.getId(), loop.getName(), loop.getLabel(), condition, preCondition);

		for(Step step: loop.getSection().getSteps()) {
			molecule.addChild(getStepMolecule(step));
		}
		
		return molecule;
	}
	
	public Molecule getRpcMolecule(RpcStep rpcStep) {
		return null;
	}
	
	public Molecule getResponseMolecule(Response response) {
		if(response instanceof TextResponse) {
			return getTextMolecule((TextResponse) response);
		} else if(response instanceof NumberResponse) {
			return getNumberMolecule((NumberResponse) response);
		} else if(response instanceof DateResponse) {
			return getDateMolecule((DateResponse) response);
		} else if(response instanceof YesNoResponse) {
			return getYesNoMolecule((YesNoResponse) response);
		} else if(response instanceof MultipleChoiceResponse) {
			return getMultipleChoiceMolecule((MultipleChoiceResponse) response);
		} else if(response instanceof SingleChoiceResponse) {
			return getSingleChoiceMolecule((SingleChoiceResponse) response);
		} 
		return null;
	}
	
	public abstract Molecule getDataStepMolecule(DataStep step);

	public abstract Molecule getWaitMolecule(Wait weight);

	public abstract Molecule getTextMolecule(TextResponse response);

	public abstract Molecule getNumberMolecule(NumberResponse response);

	public abstract Molecule getDateMolecule(DateResponse response);

	public abstract Molecule getYesNoMolecule(YesNoResponse response);
	
	public abstract Molecule getMultipleChoiceMolecule(MultipleChoiceResponse response);

	public abstract Molecule getSingleChoiceMolecule(SingleChoiceResponse response);


}
