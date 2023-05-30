package com.app.eyes.utility;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

public class Utils {
	
	public Utils() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean validatePass(String pass,String cnfPass) {
		
		return pass.equals(cnfPass);
	}
	
	public static boolean validateSession( HttpSession session, String attribute) {
		try {
			if ( null == session.getAttribute(attribute))
				return false;
			else
				return true;
		} catch (Exception e) {
			return true;
		}
	}
	
	public static String getURL( String scheme, String serverName, String serverPort, String contextPath, String newUri ) {
		String newURL	=	scheme + "://" + serverName + ":" + serverPort + contextPath + "/" + newUri;
		return newURL;
	}
	
	public static String formatDate(Date date) {
		Calendar  cal		=	Calendar.getInstance();
		try {
			cal.setTime(date);
		} catch (Exception e) {
			// TODO: handle exception
			return "-";
		}
		
		int month			=	cal.get(Calendar.MONTH);
		String monthString 	= 	new DateFormatSymbols().getShortMonths()[month];
		int day 			= 	cal.get(Calendar.DAY_OF_MONTH);
		int year 			= 	cal.get(Calendar.YEAR);
		String dateString	=	String.valueOf(day) + "-" + monthString + "-" + String.valueOf(year);	
		return dateString;
	}
	
}
