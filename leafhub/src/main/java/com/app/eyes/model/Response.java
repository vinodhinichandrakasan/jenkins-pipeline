package com.app.eyes.model;

import com.app.eyes.pojo.PatientDetailsPOJO;
import com.app.eyes.pojo.VisitDetailsPOJO;

import lombok.Data;

@Data
public class Response {
	
	private String status;
	private Object data;
	public Response(String string, PatientDetailsPOJO p) {
		// TODO Auto-generated constructor stub
		status=string;
		data=p;
	}
	
	public Response(String string, String p) {
		// TODO Auto-generated constructor stub
		status=string;
		data=p;
	}

	public Response(String string, VisitDetailsPOJO v) {
		// TODO Auto-generated constructor stub
		status=string;
		data=v;
	}
	
}
