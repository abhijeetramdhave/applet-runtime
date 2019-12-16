package com.atheer.applet.runtime.expressions;

import com.atheer.applet.runtime.AppletContext;
import com.atheer.applet.runtime.tracking.MoleculeTrackingData;
import com.atheer.applet.runtime.tracking.TrackingData;

/**
 * Resolves simple expressions like '<step-name>.<attribute-name>'.
 * 
 * @author Abhijeet Ramdhave
 */
public class SimpleExpressionResolver implements ExpressionResolver {
	
	private AppletContext context;
	
	public SimpleExpressionResolver(AppletContext context) {
		super();
		this.context = context;
	}

	@Override
	public String getValue(String expression) {
		String [] tokens = expression.split("\\.");
		
		TrackingData trackingData = context.getTrackingData();
		
		MoleculeTrackingData data = trackingData.getNameMap().get(tokens[0]);
		return data.getData().get(tokens[1]);
	}

}
