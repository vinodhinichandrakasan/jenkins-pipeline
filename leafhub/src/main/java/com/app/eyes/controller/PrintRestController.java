package com.app.eyes.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.eyes.pojo.VisitDetailsPOJO;
import com.app.eyes.service.PdfService;
import com.app.eyes.service.VisitService;

@RestController
@RequestMapping("/dashboard")
public class PrintRestController {
	private static final Logger logger = Logger.getLogger(PrintRestController.class);

	@Autowired
	VisitDetailsPOJO visit;

	@Autowired
	VisitService visitService;

	@Autowired
	PdfService pdfService;

	@RequestMapping(value= "/print/DischargeSummary_{id}.pdf")
	public String printDetails(@PathVariable("id")String id, HttpServletResponse response) {
		logger.info("In Print DischargeSummary Post");
		visit	=	visitService.findOne(Integer.parseInt(id));
		try {
			pdfService.dischargeSummary(visit, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value= "/print/Ascan_{id}.pdf")
	public String printAscan(@PathVariable("id")String id, HttpServletResponse response) {
		logger.info("In Print Ascan Report Post");
		visit	=	visitService.findOne(Integer.parseInt(id));
		try {
			pdfService.aScanReport(visit, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value= "/print/DrugPrescription_{id}.pdf")
	public String printDrug(@PathVariable("id")String id, HttpServletResponse response) {
		logger.info("In Print Drug Prescription Post");
		visit	=	visitService.findOne(Integer.parseInt(id));
		try {
			pdfService.drugPrescription(visit, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value= "/print/GlassPrescription_{id}.pdf")
	public String printGlass(@PathVariable("id")String id, HttpServletResponse response) {
		logger.info("In Print Glass Prescription Post");
		visit	=	visitService.findOne(Integer.parseInt(id));
		try {
			pdfService.glassPrescription(visit, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
