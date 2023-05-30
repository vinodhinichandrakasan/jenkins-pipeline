package com.app.eyes.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Service;

import com.app.eyes.pojo.VisitDetailsPOJO;
import com.app.eyes.service.PdfService;
import com.app.eyes.service.VisitService;
import com.app.eyes.utility.Utils;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;

@Service
@EntityScan(basePackages = {"com.app.eyes"})
public class PdfServiceImpl implements PdfService{
	private static final Logger logger = Logger.getLogger(PdfServiceImpl.class);

	@Autowired
	VisitDetailsPOJO visit;

	@Autowired
	VisitService visitService;

	@Value("${pdfTemplate.path}")
	String templatePath;

	@Value("${pdfGenerated.path}")
	String generatedPath;

	//String text	=	"\\ue0\\ua4\\ua6\\ue0\\ua4\\ubf\\ue0\\ua4\\ub5\\ue0\\ua4\\ub8";	
	//String text	=	"\u0926\u093f\u0935\u0938";

	@Override
	public String dischargeSummary( VisitDetailsPOJO visit, HttpServletResponse response) {

		File templateDirectory	=	new File(String.valueOf(templatePath));
		if( !templateDirectory.exists() ){
			logger.info("Creating a folder template");
			templateDirectory.mkdir();
		}

		File generatedDirectory	=	new File(String.valueOf(generatedPath));
		if( !generatedDirectory.exists() ){
			logger.info("Creating a folder generated");
			generatedDirectory.mkdir();
		}

		String templatePDF	=	templatePath + "dischargeTemplate.pdf";
		String generatedPDF	=	generatedPath + "DischargeSummary.pdf";
		PdfStamper stamper;
		PdfReader pdfReader;

		try {
			pdfReader	=	new PdfReader( templatePDF );
			FileOutputStream fileOS	=	new FileOutputStream( generatedPDF );
			stamper	=	new PdfStamper(pdfReader, fileOS);
			stamper.setFormFlattening(true);
			stamper.getAcroFields().setField("patientName", visit.getPatient().getFirstName() + " " + visit.getPatient().getLastName());
			stamper.getAcroFields().setField("patientId", visit.getPatient().getPatientId().toString());
			stamper.getAcroFields().setField("age", visit.getPatient().getAge().toString());
			stamper.getAcroFields().setField("gender", visit.getPatient().getGender());
			stamper.getAcroFields().setField("address", visit.getPatient().getAddress());
			stamper.getAcroFields().setField("admissionDate", Utils.formatDate(visit.getAdmissionDate()));
			stamper.getAcroFields().setField("surgeryDate", Utils.formatDate(visit.getSurgeryDate()));
			stamper.getAcroFields().setField("dischargeDate", Utils.formatDate(visit.getDischargeDate()));
			stamper.getAcroFields().setField("diagnosis", visit.getDiagnosis());
			stamper.getAcroFields().setField("surgicalProc", visit.getSurgicalProc());
			stamper.getAcroFields().setField("surgeon", visit.getSurgeon());
			stamper.getAcroFields().setField("anaesthetist", visit.getAnaesthetist());
			stamper.getAcroFields().setField("investigations", visit.getInvestigations());
			stamper.getAcroFields().setField("followUp", Utils.formatDate(visit.getFollowUp()));

			if( null != visit.getDrugs()) {
				int rowNo	=	4;
				if( visit.getDrugs().size() < rowNo )
					rowNo	=	visit.getDrugs().size();
				for ( int i = 1; i <= rowNo ; i++) {
					stamper.getAcroFields().setField("srNo" + i, String.valueOf(i));
					if ( null != visit.getDrugs().get(i-1).getDrugType() )
						stamper.getAcroFields().setField("drugType" + i, visit.getDrugs().get(i-1).getDrugType());
					else
						stamper.getAcroFields().setField("drugType" + i, "-");

					if ( null != visit.getDrugs().get(i-1).getDrugName() )
						stamper.getAcroFields().setField("drugName" + i, visit.getDrugs().get(i-1).getDrugName());
					else
						stamper.getAcroFields().setField("drugName" + i, "-");
					if (( null != String.valueOf(visit.getDrugs().get(i-1).getQuantity()) ) || 
							(!String.valueOf(visit.getDrugs().get(i-1).getQuantity()).equalsIgnoreCase("null")))
						stamper.getAcroFields().setField("quantity" + i, String.valueOf(visit.getDrugs().get(i-1).getQuantity()));
					else
						stamper.getAcroFields().setField("quantity" + i, "-");

					if (( null != String.valueOf(visit.getDrugs().get(i-1).getFrequency()) ) ||
							!String.valueOf(visit.getDrugs().get(i-1).getFrequency()).equalsIgnoreCase("null"))
						stamper.getAcroFields().setField("frequency" + i, String.valueOf(visit.getDrugs().get(i-1).getFrequency()) );
					else
						stamper.getAcroFields().setField("frequency" + i, "-");

					if (( null != String.valueOf(visit.getDrugs().get(i-1).getDuration()) ) ||
							!String.valueOf(visit.getDrugs().get(i-1).getDuration()).equalsIgnoreCase("null"))
						stamper.getAcroFields().setField("duration" + i, String.valueOf(visit.getDrugs().get(i-1).getDuration())
								+ " " + visit.getDrugs().get(i-1).getDurationType());
					else
						stamper.getAcroFields().setField("duration" + i, "-");
				}
			}
			stamper.close();
			pdfReader.close(); 

			// Show PDF in a browser
			File file	=	new File( generatedPDF );
			InputStream inputStream = new FileInputStream(file);
			IOUtils.copy( inputStream, response.getOutputStream() );
			response.setContentType("application/pdf");
			response.setHeader("content-disposition", "inline");
			response.flushBuffer();
			logger.info("Try End");

		} catch ( IOException | DocumentException | NullPointerException e ) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String drugPrescription(VisitDetailsPOJO visit, HttpServletResponse response) {
		String templatePDF	=	templatePath + "drugTemplate.pdf";
		String generatedPDF	=	generatedPath + "DrugPrescription.pdf";
		PdfStamper stamper;
		PdfReader pdfReader;
		try {
			pdfReader	=	new PdfReader( templatePDF );
			FileOutputStream fileOS	=	new FileOutputStream( generatedPDF );
			stamper	=	new PdfStamper(pdfReader, fileOS);
			stamper.setFormFlattening(true);
			stamper.getAcroFields().setField("patientName", visit.getPatient().getFirstName() + " " + visit.getPatient().getLastName());
			stamper.getAcroFields().setField("patientId", visit.getPatient().getPatientId().toString());
			stamper.getAcroFields().setField("age", visit.getPatient().getAge().toString());
			stamper.getAcroFields().setField("gender", visit.getPatient().getGender());
			stamper.getAcroFields().setField("address", visit.getPatient().getAddress());
			stamper.getAcroFields().setField("visitDate", Utils.formatDate(visit.getVisitDate()));
			if( null != visit.getDrugs()) {
				int rowNo	=	4;
				if( visit.getDrugs().size() < rowNo )
					rowNo	=	visit.getDrugs().size();

				for ( int i = 1; i <= rowNo ; i++) {
					stamper.getAcroFields().setField("srNo" + i, String.valueOf(i));
					if ( null != visit.getDrugs().get(i-1).getDrugType() )
						stamper.getAcroFields().setField("drugType" + i, visit.getDrugs().get(i-1).getDrugType());
					else
						stamper.getAcroFields().setField("drugType" + i, "-");

					if ( null != visit.getDrugs().get(i-1).getDrugName() )
						stamper.getAcroFields().setField("drugName" + i, visit.getDrugs().get(i-1).getDrugName());
					else
						stamper.getAcroFields().setField("drugName" + i, "-");

					if (( null != String.valueOf(visit.getDrugs().get(i-1).getQuantity()) ) || 
							!String.valueOf(visit.getDrugs().get(i-1).getQuantity()).equalsIgnoreCase("null"))
						stamper.getAcroFields().setField("quantity" + i, String.valueOf(visit.getDrugs().get(i-1).getQuantity()));
					else
						stamper.getAcroFields().setField("quantity" + i, "-");

					if (( null != String.valueOf(visit.getDrugs().get(i-1).getFrequency()) ) ||
							!String.valueOf(visit.getDrugs().get(i-1).getFrequency()).equalsIgnoreCase("null"))
						stamper.getAcroFields().setField("frequency" + i, String.valueOf(visit.getDrugs().get(i-1).getFrequency()) );
					else
						stamper.getAcroFields().setField("frequency" + i, "-");

					if (( null != String.valueOf(visit.getDrugs().get(i-1).getDuration()) ) ||
							!String.valueOf(visit.getDrugs().get(i-1).getDuration()).equalsIgnoreCase("null"))
						stamper.getAcroFields().setField("duration" + i, String.valueOf(visit.getDrugs().get(i-1).getDuration())
								+ " " + visit.getDrugs().get(i-1).getDurationType());
					else
						stamper.getAcroFields().setField("duration" + i, "-");
					

				}
			}
			stamper.close();
			pdfReader.close(); 

			// Show PDF in a browser
			File file	=	new File( generatedPDF );
			InputStream inputStream = new FileInputStream(file);
			IOUtils.copy( inputStream, response.getOutputStream() );
			response.setContentType("application/pdf");
			response.setHeader("content-disposition", "inline");
			response.flushBuffer();
			logger.info("Try End");

		} catch ( IOException | DocumentException | NullPointerException e ) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String glassPrescription(VisitDetailsPOJO visit, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String templatePDF	=	templatePath + "glassTemplate.pdf";
		String generatedPDF	=	generatedPath + "GlassPrescription.pdf";
		PdfStamper stamper;
		PdfReader pdfReader;
		try {
			pdfReader	=	new PdfReader( templatePDF );
			FileOutputStream fileOS	=	new FileOutputStream( generatedPDF );
			stamper	=	new PdfStamper(pdfReader, fileOS);
			stamper.setFormFlattening(true);
			stamper.getAcroFields().setField("patientName", visit.getPatient().getFirstName() + " " + visit.getPatient().getLastName());
			stamper.getAcroFields().setField("patientId", visit.getPatient().getPatientId().toString());
			stamper.getAcroFields().setField("age", visit.getPatient().getAge().toString());
			stamper.getAcroFields().setField("gender", visit.getPatient().getGender());
			stamper.getAcroFields().setField("address", visit.getPatient().getAddress());
			stamper.getAcroFields().setField("sphRight", visit.getSphDistRight());
			stamper.getAcroFields().setField("sphLeft", visit.getSphDistLeft());
			stamper.getAcroFields().setField("cylRight", visit.getCylDistRight());
			stamper.getAcroFields().setField("cylLeft", visit.getCylDistLeft());
			stamper.getAcroFields().setField("axisRight", visit.getAxisDistRight().toString());
			stamper.getAcroFields().setField("axisLeft", visit.getAxisDistLeft().toString());
			stamper.getAcroFields().setField("vaRight", visit.getVaDistRight());
			stamper.getAcroFields().setField("vaLeft", visit.getVaDistLeft());
			stamper.getAcroFields().setField("nearSphRight", visit.getSphNearRight());
			stamper.getAcroFields().setField("nearSphLeft", visit.getSphNearLeft());
			stamper.getAcroFields().setField("nearCylRight", visit.getCylNearRight());
			stamper.getAcroFields().setField("nearCylLeft", visit.getCylNearLeft());
			stamper.getAcroFields().setField("nearAxisRight", visit.getAxisNearRight().toString());
			stamper.getAcroFields().setField("nearAxisLeft", visit.getAxisNearLeft().toString());
			stamper.getAcroFields().setField("nearVaRight", visit.getVaNearRight());
			stamper.getAcroFields().setField("nearVaLeft", visit.getVaNearLeft());
			stamper.getAcroFields().setField("visitDate", Utils.formatDate(visit.getVisitDate()));
			stamper.close();
			pdfReader.close(); 

			// Show PDF in a browser
			File file	=	new File( generatedPDF );
			InputStream inputStream = new FileInputStream(file);
			IOUtils.copy( inputStream, response.getOutputStream() );
			response.setContentType("application/pdf");
			response.setHeader("content-disposition", "inline");
			response.flushBuffer();
			logger.info("Try End");

		} catch ( IOException | DocumentException | NullPointerException e ) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String aScanReport(VisitDetailsPOJO visit, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String templatePDF	=	templatePath + "ascanTemplate.pdf";
		String generatedPDF	=	generatedPath + "AscanReport.pdf";
		PdfStamper stamper;
		PdfReader pdfReader;
		try {
			pdfReader	=	new PdfReader( templatePDF );
			FileOutputStream fileOS	=	new FileOutputStream( generatedPDF );
			stamper	=	new PdfStamper(pdfReader, fileOS);
			stamper.setFormFlattening(true);
			stamper.getAcroFields().setField("patientName", visit.getPatient().getFirstName() + " " + visit.getPatient().getLastName());
			stamper.getAcroFields().setField("patientId", visit.getPatient().getPatientId().toString());
			stamper.getAcroFields().setField("age", visit.getPatient().getAge().toString());
			stamper.getAcroFields().setField("gender", visit.getPatient().getGender());
			stamper.getAcroFields().setField("address", visit.getPatient().getAddress());
			stamper.getAcroFields().setField("k1", visit.getKeraK1().toString());
			stamper.getAcroFields().setField("k2", visit.getKeraK2().toString());
			stamper.getAcroFields().setField("axialLength", visit.getAxialLength().toString());
			stamper.getAcroFields().setField("iolPower", visit.getIolPower().toString());
			stamper.getAcroFields().setField("visitDate", Utils.formatDate(visit.getVisitDate()));
			stamper.close();
			pdfReader.close(); 

			// Show PDF in a browser
			File file	=	new File( generatedPDF );
			InputStream inputStream = new FileInputStream(file);
			IOUtils.copy( inputStream, response.getOutputStream() );
			response.setContentType("application/pdf");
			response.setHeader("content-disposition", "inline");
			response.flushBuffer();
			logger.info("Try End");

		} catch ( IOException | DocumentException | NullPointerException e ) {
			e.printStackTrace();
		}
		return null;
	}
}
