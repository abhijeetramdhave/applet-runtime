package com.atheer.applet.runtime.tracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.atheer.applet.design.Applet;
import com.atheer.applet.runtime.delivery.CompositeMolecule;
import com.atheer.applet.runtime.delivery.Molecule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TrackingService {
	
	public TrackingData createTrackingData(Molecule molecule, User user) {
		TrackingData data = new TrackingData(
				generateId(), molecule.getId(), Arrays.asList(user));
		
		createMoleculeData(molecule, data.getMoleculeData());
		data.populateMap();
		return data;
	}
	
	private void createMoleculeData(Molecule molecule, List<MoleculeTrackingData> data) {
		MoleculeTrackingData moleculeData = new MoleculeTrackingData(
				generateId(), molecule.getId(), molecule.getName(), molecule.getType().toString());
		data.add(moleculeData);
		
		if(molecule instanceof CompositeMolecule) {
			CompositeMolecule composite = (CompositeMolecule) molecule;
			for(Molecule child: composite.getChildren()) {
				createMoleculeData(child, data);
			}
		}
	}
	
	public void save(TrackingData trackingData, String fileName) throws Exception {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
			
		FileWriter writer = new FileWriter(fileName);
		writer.write(gson.toJson(trackingData));
		writer.close();
	}
	
	public TrackingData restore(String fileName) throws Exception {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		TrackingData trackingData = gson.fromJson(reader, TrackingData.class);
		return trackingData;
	}
	
	public TrackingData getTrackingData(Molecule molecule) {
		return new TrackingData();
	}

	public void saveTrackingData(TrackingData trackingData) {
		try {
			save(trackingData, trackingData.getId());
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private String generateId() {
		UUID id = UUID.randomUUID();
		return id.toString();
	}
	

}
