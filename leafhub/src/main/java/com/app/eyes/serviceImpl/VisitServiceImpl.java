package com.app.eyes.serviceImpl;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.eyes.pojo.VisitDetailsPOJO;
import com.app.eyes.service.VisitService;

public class VisitServiceImpl {
	private static final Logger logger = Logger.getLogger(VisitServiceImpl.class);
	
	@Autowired
	VisitService visitService;
	
	@Transactional
	VisitDetailsPOJO addVisit(VisitDetailsPOJO visitDetails) {
		logger.info("VisitServiceImpl , Visit ::: " + visitDetails.toString() );
		setDefault(visitDetails);
		VisitDetailsPOJO visitAdd	=	visitService.save(visitDetails);
		logger.info("Returned Object::: " + visitAdd.toString());
		if( !visitAdd.getVisitId().equals(null)){
			return visitAdd;
		}
		else {
			logger.info("Visit could not be created !!!");
			return null;
		}
	}
	
	void setDefault(VisitDetailsPOJO visitDetails) {
		logger.info("SetDefault method");
		if ( null == visitDetails.getAxisDistLeft())
			visitDetails.setAxisDistLeft(0);
		if ( null == visitDetails.getAxisDistRight())
			visitDetails.setAxisDistRight(0);
		if ( null == visitDetails.getAxisNearLeft())
			visitDetails.setAxisNearLeft(0);
		if ( null == visitDetails.getAxisNearRight())
			visitDetails.setAxisNearRight(0);
		if ( null == visitDetails.getIopRight())
			visitDetails.setIopRight(0);
		if ( null == visitDetails.getIopLeft())
			visitDetails.setIopLeft(0);
		if ( null == visitDetails.getKeraK1())
			visitDetails.setKeraK1(0.00F);
		if ( null == visitDetails.getKeraK2())
			visitDetails.setKeraK2(0.00F);
		if ( null == visitDetails.getAxialLength())
			visitDetails.setAxialLength(0.00F);
		if ( null == visitDetails.getIolPower())
			visitDetails.setIolPower(0.00F);
		/*if ( null == visitDetails.getFollowUp())
			visitDetails.setFollowUp(0);*/
		
	}
	
	void clearForm(VisitDetailsPOJO visitDetails) {
		logger.info("Clearing Form");
		visitDetails.setSphDistRight(null);
		visitDetails.setSphDistLeft(null);
		visitDetails.setCylDistRight(null);
		visitDetails.setCylDistLeft(null);
		visitDetails.setAxisDistRight(null);
		visitDetails.setAxisDistLeft(null);
		visitDetails.setVaDistRight(null);
		visitDetails.setVaDistLeft(null);
		visitDetails.setSphNearRight(null);
		visitDetails.setSphNearLeft(null);
		visitDetails.setCylNearRight(null);
		visitDetails.setCylNearLeft(null);
		visitDetails.setAxisNearRight(null);
		visitDetails.setAxisNearLeft(null);
		visitDetails.setVaNearRight(null);
		visitDetails.setVaNearLeft(null);
		visitDetails.setRemarksLeft(null);
		visitDetails.setRemarksRight(null);
		visitDetails.setRemarksLeftDesc(null);
		visitDetails.setRemarksRightDesc(null);
		visitDetails.setAnteriorSeg(null);
		visitDetails.setPosteriorSeg(null);
		visitDetails.setIopLeft(null);
		visitDetails.setIopRight(null);
		visitDetails.setComplaints(null);
		visitDetails.setKeraK1(null);
		visitDetails.setKeraK2(null);
		visitDetails.setAxialLength(null);
		visitDetails.setIolPower(null);
		visitDetails.setSurgicalPlan(null);
		visitDetails.setAdmissionDate(null);
		visitDetails.setSurgeryDate(null);
		visitDetails.setDischargeDate(null);
		visitDetails.setDiagnosis(null);
		visitDetails.setSurgicalProc(null);
		visitDetails.setInvestigations(null);
		visitDetails.setAnaesthetist(null);
		visitDetails.setSurgeon(null);
		visitDetails.setFollowUp(null);
	}
}
