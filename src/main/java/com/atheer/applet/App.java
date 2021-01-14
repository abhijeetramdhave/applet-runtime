package com.atheer.applet;

import java.util.Arrays;

import com.atheer.applet.design.Applet;
import com.atheer.applet.design.Branch;
import com.atheer.applet.design.BranchMode;
import com.atheer.applet.design.Condition;
import com.atheer.applet.design.ConditionalSection;
import com.atheer.applet.design.DataStep;
import com.atheer.applet.design.DateResponse;
import com.atheer.applet.design.Lane;
import com.atheer.applet.design.NumberResponse;
import com.atheer.applet.design.Operator;
import com.atheer.applet.design.Section;
import com.atheer.applet.design.SingleChoiceResponse;
import com.atheer.applet.design.Step;
import com.atheer.applet.design.TextResponse;
import com.atheer.applet.design.Wait;
import com.atheer.applet.design.YesNoResponse;
import com.atheer.applet.persistence.Repository;
import com.atheer.applet.runtime.AppletRuntime;
import com.atheer.applet.runtime.delivery.console.DateMolecule;
import com.atheer.applet.runtime.delivery.console.MultipleChoiceMolecule;
import com.atheer.applet.runtime.delivery.console.NavigableUIMolecule;
import com.atheer.applet.runtime.delivery.console.NumberMolecule;
import com.atheer.applet.runtime.delivery.console.TextMolecule;
import com.atheer.applet.runtime.delivery.console.WaitMolecule;
import com.atheer.applet.runtime.delivery.console.YesNoMolecule;
import com.atheer.applet.runtime.tracking.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	Applet applet = createSimpleApplet();
    	Repository repository = new Repository();
    	repository.save(applet, "applet.json");

    	AppletRuntime runtime = new AppletRuntime();
    	runtime.launch(applet, new User("USER001", "Abhijeet"));
    	
//    	applet = repository.restore("applet.json");
//    	repository.save(applet, "applet-restored.json");
    	
//        renderSampleForm();
//        renderWaitForm();
    }
    
    private static void renderWaitForm() {
        WaitMolecule form = new WaitMolecule(
        		"M00001", "wait", "Wait!",
        		"Vehicle Maintenance", 
        		"Wait for car wash!", "");
        form.setSeconds(10);
        form.setMessages(Arrays.asList("Hi", "Please wait!", "Vehicle is getting washed."));
        form.render();
    }

	private static void renderSampleForm() {
        NavigableUIMolecule form = new NavigableUIMolecule(
        		"S00001", "vehicleDetails", "Vehicle Details",
        		"Vehicle Maintenance", 
        		"Inspection", "Get all the vehicle details.");

        
        TextMolecule vehicleNumber = new TextMolecule("IN00001", "vehicleNumber", "Vehicle Number");
        vehicleNumber.setRequired(true);
        form.addChild(vehicleNumber);

        TextMolecule owner = new TextMolecule("IN00002", "ownerName", "Owner Name");
        owner.setHintText("Enter owner's name here.");
        form.addChild(owner);
        
        NumberMolecule year = new NumberMolecule("IN00003", "yearOfPassing", "Year of passing");
        year.setMinValue(2000.0);
        year.setMaxValue(2019.0);
        form.addChild(year);
        
        form.addChild(new YesNoMolecule("IN00004", "insurance", "Insurance Taken?"));
        
        MultipleChoiceMolecule policyDetails = new MultipleChoiceMolecule("IN00005", "policyDetails", "If yes, select applicable");
        policyDetails.setChoices(Arrays.asList("Basic Third Party Liability", "LL to Paid Driver", "PA Cover for Owner Driver", "PA Cover for Un-named Persons" ));
		form.addChild(policyDetails);
        
        DateMolecule date = new DateMolecule("IN00006", "expiryDate", "Insurance expires on");
        form.addChild(date);

        form.render();
	}

    public static Applet createSimpleApplet() {
    	Applet applet = new Applet("APP00001", "theApplet", "Applet Demo");
    	applet.addLane(new Lane("LAN00001", "theLane", "Default Lane"));
    	applet.getLanes().get(0).addSection(createSimpleSection());
    	return applet;
    }

    private static Branch createBranch() {
    	Branch branch = new Branch("BR00001", "branch001", "Multi-path Branching");
    	branch.getPaths().add(getNewVehiclePath());
    	branch.getPaths().add(getOldVehiclePath());
    	branch.setDefaultPath(getDefaultPath());
    	branch.setMode(BranchMode.All);
    	return branch;
    }

	private static ConditionalSection getNewVehiclePath() {
		Condition condition = new Condition("vehicle.yearOfPassing", Operator.GreaterThanEquals, "2018");
		
		DataStep step1 = new DataStep("STP00010", "step10", "Action 10");
		step1.setDescription("Perform action 10.");
	
		YesNoResponse action1 = new YesNoResponse("RES00010", "response10", "Done?");
		step1.addResponse(action1);

		DataStep step2 = new DataStep("STP00011", "step11", "Action 11");
		step2.setDescription("Perform action 11.");
	
		YesNoResponse action2 = new YesNoResponse("RES00011", "response11", "Done?");
		step2.addResponse(action2);

		ConditionalSection section = new ConditionalSection("SEC00002", "newVehiclePath", "Branch: Year of passing >= 2018", condition);
		section.addStep(step1);
		section.addStep(step2);
		
		return section;
	}

	private static ConditionalSection getOldVehiclePath() {
		Condition condition = new Condition("vehicle.yearOfPassing", Operator.LessThan, "2018");
		
		DataStep step1 = new DataStep("STP00020", "step20", "Action 20");
		step1.setDescription("Perform action 20.");
	
		YesNoResponse action1 = new YesNoResponse("RES00020", "response20", "Done?");
		step1.addResponse(action1);

		DataStep step2 = new DataStep("STP00021", "step21", "Action 21");
		step2.setDescription("Perform action 21.");
	
		YesNoResponse action2 = new YesNoResponse("RES00021", "response21", "Done?");
		step2.addResponse(action2);

		ConditionalSection section = new ConditionalSection("SEC00003", "oldVehiclePath", "Branch: Year of passing < 2018", condition);
		section.addStep(step1);
		section.addStep(step2);
		
		return section;
	}

	private static Section getDefaultPath() {
		DataStep step1 = new DataStep("STP00030", "step30", "Default Action 01");
		step1.setDescription("Perform default action 01.");
	
		YesNoResponse action1 = new YesNoResponse("RES00030", "response30", "Done?");
		step1.addResponse(action1);

		DataStep step2 = new DataStep("STP00031", "step31", "Action 31");
		step2.setDescription("Perform default action 02.");
	
		YesNoResponse action2 = new YesNoResponse("RES00031", "response31", "Done?");
		step2.addResponse(action2);

		Section section = new Section("SEC00004", "defaultPath", "Branch: Default Path");
		section.addStep(step1);
		section.addStep(step2);
		
		return section;
	}

	private static Section createSimpleSection() {
		Section section = new Section("SEC00001", "dataSection", "Data Elements and Navigation");
		section.addStep(vehicleDetails());
		section.addStep(ownerDetails());
		section.addStep(insuranceDetails());
		section.addStep(inspectionRide());
		section.addStep(createBranch());
		section.addStep(finalStep());
		return section;
	}

	private static Step finalStep() {
		DataStep step = new DataStep("STP00040", "washing", "Washing");
		step.setDescription("Make sure the vehicle gets proper washing..");
	
		YesNoResponse action = new YesNoResponse("RES00040", "response40", "Done?");
		step.addResponse(action);
		return step;
	}

	private static Step vehicleDetails() {
		DataStep step = new DataStep("STP00001", "vehicle", "Vehicle Details");
		step.setDescription("Please provide vehicle details.");
		
		TextResponse vehicleNumber = new TextResponse("RES00001", "vehicleNumber", "Vehicle Number");
		vehicleNumber.setRequired(true);
		vehicleNumber.setMaxLength(20);

		SingleChoiceResponse brand = new SingleChoiceResponse("RES00002", "brand", "Brand");
		brand.setChoices(Arrays.asList("Maruti Suzuki", "Tata", "Honda"));

		NumberResponse yearOfPassing = new NumberResponse("RES00003", "yearOfPassing", "Year of Passing");
		yearOfPassing.setMaxValue(2019.0);
		yearOfPassing.setMinValue(2000.0);
		yearOfPassing.setRequired(true);
		
		step.addResponse(vehicleNumber).addResponse(brand).addResponse(yearOfPassing);
		
		return step;
	}

	private static Step ownerDetails() {
		DataStep step = new DataStep("STP00002", "owner", "Owner Details");
		step.setDescription("Please provide owner details.");
	
		TextResponse ownerName = new TextResponse("RES00004", "ownerName", "Owner Name");
		ownerName.setMaxLength(40);

		TextResponse contactNumber = new TextResponse("RES00005", "contactNumber", "Contact Number");
		contactNumber.setRequired(true);
		contactNumber.setMaxLength(40);

		step.addResponse(ownerName).addResponse(contactNumber);
		
		return step;
	}

	private static Step insuranceDetails() {
		DataStep step = new DataStep("STP00003", "insurance", "Insurance Details");
		step.setDescription("Please provide insurance details.");
		
		YesNoResponse insured = new YesNoResponse("RES00006", "insurance", "Insurance Taken");
		
		DateResponse expiresOn = new DateResponse("RES00007", "expiryDate", "Expires On", "dd/mm/yyyy");

		step.addResponse(insured).addResponse(expiresOn);
		
		return step;
	}

	private static Step inspectionRide() {
		Wait wait = new Wait("STP00004", "inspectionRide", "Inspection Ride");
		wait.setSeconds(12);
		wait.setMessages(Arrays.asList(
			"Hey!",
			"Wait till inspection ride is done.",
			"Till then, let us know about applet authoring.",
			"Applet supports multi-path branching with support of any/all modes.",
			"Forward and backword navigation is possible.",
			"You can use system molecules to fetch data or perform computation.",
			"You can introduce wait step and educate user about the flow while waiting :)",
			"Don't forget to see tracking model.",
			"You could potentially suspend the flow and resume later.",
			"Wondering if looping should be supported.",
			"If so, we need to track data for every iteration."
		));
		
		return wait;
	}

}
