package com.atheer.applet.runtime.delivery.console;

import java.util.Arrays;

import com.atheer.applet.design.DataStep;
import com.atheer.applet.design.DateResponse;
import com.atheer.applet.design.MultipleChoiceResponse;
import com.atheer.applet.design.NumberResponse;
import com.atheer.applet.design.Response;
import com.atheer.applet.design.SingleChoiceResponse;
import com.atheer.applet.design.TextResponse;
import com.atheer.applet.design.Wait;
import com.atheer.applet.design.YesNoResponse;
import com.atheer.applet.runtime.MoleculeFactory;
import com.atheer.applet.runtime.delivery.Molecule;

public class ConsoleMoleculeFactory extends MoleculeFactory {

	@Override
	public Molecule getDataStepMolecule(DataStep step) {
		NavigableUIMolecule molecule = new NavigableUIMolecule(step.getId(), step.getName(), step.getLabel(),
				getAppletLabel(), getSectionLabel(), step.getDescription());

		for(Response response: step.getResponses()) {
			molecule.addChild((UIMolecule) getResponseMolecule(response));
		}
		return molecule;
	}
	
	@Override
	public Molecule getWaitMolecule(Wait step) {
		WaitMolecule molecule = new WaitMolecule(
				step.getId(), step.getName(), step.getLabel(),
				getAppletLabel(), getSectionLabel(), "");
		molecule.setSeconds(step.getSeconds());
		molecule.setMessages(step.getMessages());

		return molecule;
	}

	@Override
	public Molecule getTextMolecule(TextResponse response) {
		TextMolecule molecule = new TextMolecule(response.getId(), response.getName(), response.getLabel());
		molecule.setRequired(response.getRequired());
		molecule.setHintText(response.getHintText());
		molecule.setMaxLength(response.getMaxLength());
		
		return molecule;
	}

	@Override
	public Molecule getNumberMolecule(NumberResponse response) {
		NumberMolecule molecule = new NumberMolecule(response.getId(), response.getName(), response.getLabel());
		molecule.setRequired(response.getRequired());
		molecule.setMinValue(response.getMinValue());
		molecule.setMaxValue(response.getMaxValue());
		
		return molecule;
	}

	@Override
	public Molecule getDateMolecule(DateResponse response) {
		DateMolecule molecule = new DateMolecule(response.getId(), response.getName(), response.getLabel());
		molecule.setRequired(response.getRequired());
		molecule.setFormat(response.getFormat());
		
		return molecule;
	}

	@Override
	public Molecule getYesNoMolecule(YesNoResponse response) {
		YesNoMolecule molecule = new YesNoMolecule(response.getId(), response.getName(), response.getLabel());
		molecule.setRequired(response.getRequired());
		
		return molecule;
	}

	@Override
	public Molecule getMultipleChoiceMolecule(MultipleChoiceResponse response) {
		MultipleChoiceMolecule molecule = new MultipleChoiceMolecule(response.getId(), response.getName(), response.getLabel());
		molecule.setRequired(response.getRequired());
		molecule.setChoices(response.getChoices());
		molecule.setHintText("Select all applicable");
		
		return molecule;
	}

	@Override
	public Molecule getSingleChoiceMolecule(SingleChoiceResponse response) {
		SingleChoiceMolecule molecule = new SingleChoiceMolecule(response.getId(), response.getName(), response.getLabel());
		molecule.setRequired(response.getRequired());
		molecule.setChoices(response.getChoices());
		molecule.setHintText("Select one");
		
		return molecule;
	}


}
