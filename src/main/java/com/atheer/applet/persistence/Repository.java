package com.atheer.applet.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import com.atheer.applet.design.Applet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Repository {
	
	public void save(Applet applet, String fileName) throws Exception {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();

//		RuntimeTypeAdapterFactory<Step> stepFactory = RuntimeTypeAdapterFactory
//			    .of(Step.class, "type", true)
//			    .registerSubtype(DataStep.class)
//			    .registerSubtype(RpcStep.class)
//			    .registerSubtype(Branch.class)
//			    .registerSubtype(Loop.class);
//
//		builder.registerTypeAdapterFactory(stepFactory);

//		RuntimeTypeAdapterFactory<Response> responseFactory = RuntimeTypeAdapterFactory
//			    .of(Response.class, "type", true)
//			    .registerSubtype(DateResponse.class, ResponseType.Date.toString())
//			    .registerSubtype(MultipleChoiceResponse.class, ResponseType.MultipleChoice.toString())
//			    .registerSubtype(NumberResponse.class, ResponseType.Number.toString())
//			    .registerSubtype(SingleChoiceResponse.class, ResponseType.SingleChoice.toString())
//			    .registerSubtype(TextResponse.class, ResponseType.Text.toString())
//			    .registerSubtype(YesNoResponse.class, ResponseType.YesNo.toString());
//	
//		builder.registerTypeAdapterFactory(responseFactory);

		Gson gson = builder.create();
			
		FileWriter writer = new FileWriter(fileName);
		writer.write(gson.toJson(applet));
		writer.close();
	}
	
	public Applet restore(String fileName) throws Exception {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		Applet applet = gson.fromJson(reader, Applet.class);
		return applet;
	}
}
