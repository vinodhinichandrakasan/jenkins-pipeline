package com.app.eyes.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.eyes.model.ApiResponse;
import com.app.eyes.model.Response;
import com.app.eyes.pojo.PatientDetailsPOJO;
import com.app.eyes.service.PatientService;

@RestController
@RequestMapping("/api")
public class PatientRestController {
	
	@Autowired
	PatientService patientService;
	
	private static final Logger logger = Logger.getLogger(PatientRestController.class);
	
	@RequestMapping(value= "/learner/delete",method=RequestMethod.POST)
	public Response deletePatient(@RequestBody PatientDetailsPOJO p ) {
		logger.info("Patient Delete Post");
		logger.info("Patient Id : " + p.getPatientId());
		try {
			patientService.delete(p);
			return new Response("Done",p);
		} catch (Exception e) {
			logger.info("Could Not Delete : ", e);
			return new Response("Error",p);
		}
	}
	
	@RequestMapping(value= "/learner/edit",method=RequestMethod.POST)
	public Response editPatient(@RequestBody PatientDetailsPOJO p ) {
		logger.info("Patient Edit Post");
		logger.info("Patient Id : " + p.getPatientId());
		try {
			if (1 == patientService.editPatientDetails(p.getPatientId(),p.getTitle(), p.getFirstName(), p.getMiddleName(), p.getLastName(),
					p.getGender(), p.getEmail(), p.getMobile(), p.getPhoneNumber())) {
				logger.info("Edited Successfully");
				logger.info(p);
				return new Response("Done",p);
			}
			else
				return new Response("Error",p);
		} catch (Exception e) {
			logger.info("Could Not Edit : ", e);
			return new Response("Error",p);
		}
	}
	
	@PostMapping("/learner/add")
	public ResponseEntity<ApiResponse> createPatient(@RequestBody PatientDetailsPOJO patient) {
        ApiResponse response = new ApiResponse();
        logger.info("Patient Add Post");

        try {
            logger.info("Patient Add Try Block");
            logger.info("BirthDate : " + patient.getBirthDate());
            patient.setFirstVisitDate(new Date());
            String newId = patientService.add(patient);

            if(null != newId){
                logger.info("Patient Successfully Added");
                response.setMessage("Patient Successfully Added");
                response.setData(newId);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            
            logger.info("Failed to Add.");
            response.setMessage("Failed to Add");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } catch (Exception e) {
            logger.error("Error occurred during patient creation", e);
            response.setMessage("An error occurred");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } 
    }
	
}
