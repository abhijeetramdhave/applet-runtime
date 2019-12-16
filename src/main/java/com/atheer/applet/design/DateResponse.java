package com.atheer.applet.design;

import java.util.Date;

public class DateResponse extends Response {

	private Date value;
	private String format;
	
	public DateResponse() {
		super();
		this.type = ResponseType.Date.toString();
	}

	public DateResponse(String id, String name, String label, String format) {
		super(id, name, label);
		this.format = format;
		this.type = ResponseType.Date.toString();
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	
}
