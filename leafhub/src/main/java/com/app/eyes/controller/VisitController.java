package com.app.eyes.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.eyes.model.Response;
import com.app.eyes.pojo.DrugDetailsPOJO;
import com.app.eyes.pojo.PatientDetailsPOJO;
import com.app.eyes.pojo.VisitDetailsPOJO;
import com.app.eyes.service.PatientService;
import com.app.eyes.service.VisitService;
import com.app.eyes.utility.Utils;

@RestController
@RequestMapping("/dashboard")
public class VisitController {

	private static final Logger logger = Logger.getLogger(VisitController.class);

	@Autowired
	VisitDetailsPOJO visit;
	
	@Autowired
	VisitService visitService;
	
	@Autowired
	PatientDetailsPOJO patient;
	
	@Autowired
	PatientService patientService;

	@RequestMapping(value="/visit" , method=RequestMethod.GET)
	public ModelAndView patient( Model model, HttpSession session) {
		logger.info("Visit Home Get");
		if( Utils.validateSession(session, "UserDetails") == false) {
			logger.info("Session Attribute is Null");
			logger.info("You are not logged in. Redirecting to Login Page");
			//return "redirect:/";
			return new ModelAndView("redirect:/");
		}
		else {
			//return "visit";
			return new ModelAndView("visit");
		}
	}

	@RequestMapping(value="/newVisit/{id}",method=RequestMethod.GET)
	public ModelAndView showVisit( @PathVariable("id") String id , Model model, HttpSession session ) {
		logger.info("New Visit Get");
		logger.info("Patient Id : " + id);
		if( Utils.validateSession(session, "UserDetails") == false) {
			logger.info("Session Attribute is Null");
			logger.info("You are not logged in. Redirecting to Login Page");
			return new ModelAndView("redirect:/");
		}
		else {
			visitService.clearForm(visit);
			List<DrugDetailsPOJO> drugsList	=	new ArrayList<>();
			patient	=	patientService.findOne(Integer.parseInt(id));
			model.addAttribute("patient", patient);
			model.addAttribute("newVisit",visit);
			model.addAttribute("drugsList", drugsList);
			return new ModelAndView("visitAdd");
		}
	}

	@RequestMapping(value = "/newVisit/{id}", method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<Response> addVisit(HttpServletRequest request, @PathVariable("id")String id, @RequestBody VisitDetailsPOJO v){	
		logger.info("New Visit Post");
		String redirectUrl	=	Utils.getURL(request.getScheme(), request.getServerName(), 
								Integer.toString(request.getServerPort()) , request.getContextPath(), "dashboard/visit");
		
		try {
			logger.info("New Visit Try");
			patient	=	patientService.findOne(Integer.parseInt(id));
			v.setVisitDate(new Date());
			v.setPatient(patient);
			if( null != patient ) {
				logger.info("Visit Details : " + v.toString());
				visit	=	visitService.addVisit(v);
				if( null != visit ) {
					logger.info("Visit Successfully Created");
					return new ResponseEntity<Response>(new Response("Done", redirectUrl), HttpStatus.OK);
				}
				logger.info("Visit could not be added");
				return new ResponseEntity<Response>(new Response("Error", "f"), HttpStatus.OK);
			}
			return new ResponseEntity<Response>(new Response("Error", "f"), HttpStatus.OK);
		} catch (Exception e) {
			logger.info("New Visit Catch : " + e);
			return new ResponseEntity<Response>(new Response("Error", "f"), HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/visitSearch" , method=RequestMethod.GET)
	public ModelAndView visitSearch(Model model, HttpSession session) {
		logger.info("Visit Search Get");
		if( Utils.validateSession(session, "UserDetails") == false) {
			logger.info("Session Attribute is Null");
			logger.info("You are not logged in. Redirecting to Login Page");
			return new ModelAndView("redirect:/");
		}
		else {
			
			model.addAttribute("visitPojo", visit);
			return new ModelAndView("visitSearch");
		}
	}
	
	@SuppressWarnings("finally")
	@RequestMapping(value="/visitSearch" , method=RequestMethod.POST)
	public ModelAndView visitSearchResult( @ModelAttribute("visitPojo")VisitDetailsPOJO visit, Model model, HttpSession session) {
		logger.info("Visit Search Post");
		List<VisitDetailsPOJO> visits	=	new ArrayList<>();
		try {
			logger.info("Try");
			if( visit.getSearchFilter().equalsIgnoreCase("date")) {
				visits	=	visitService.findVisitByDate(visit.getSearchDateFrom(), visit.getSearchDateTo());
			}else {
				visits	=	visitService.findVisitByName(visit.getSearchText().toLowerCase());
			}
			logger.info("List : " + visits.toString());
			logger.info("Try end");
		} catch (Exception e) {
			logger.info("catch : " + e);
		}
		finally {
			logger.info("Finally Block");
			if(visits.contains(null)) {
				logger.info("Nothing found");
			}
			else {
				model.addAttribute("visitList", visits);
			}
			return new ModelAndView("visitSearch");
		}
	}
}
