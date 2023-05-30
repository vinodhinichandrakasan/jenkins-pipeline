package com.app.eyes.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
//@EntityListeners(VisitServiceImpl.class)
@Table(name="VisitDetails")
@Component
public class VisitDetailsPOJO {
	
	@Id
	@SequenceGenerator(name="Visit_SEQ", sequenceName="Visit_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Visit_SEQ")
	@Column(unique = true, nullable = false)
	private Integer visitId;
	
	@ManyToOne
	@JoinColumn( name = "patientId", nullable = false )
	private PatientDetailsPOJO patient;
	
	// Visit Date
	
	@Column(unique = false, nullable = false)
	private Date visitDate;
	
	// Glass Prescription Details
	
	@Column(unique = false, nullable = true, length=10)
	private String sphDistLeft;
	
	@Column(unique = false, nullable = true, length=10)
	private String sphDistRight;
	
	@Column(unique = false, nullable = true, length=10)
	private String cylDistLeft;
	
	@Column(unique = false, nullable = true, length=10)
	private String cylDistRight;
	
	@Column(unique = false, nullable = true, length=3)
	private Integer axisDistLeft;
	
	@Column(unique = false, nullable = true, length=3)
	private Integer axisDistRight;
	
	@Column(unique = false, nullable = true, length=10)
	private String vaDistLeft;
	
	@Column(unique = false, nullable = true, length=10)
	private String vaDistRight;
	
	@Column(unique = false, nullable = true, length=10)
	private String sphNearLeft;
	
	@Column(unique = false, nullable = true, length=10)
	private String sphNearRight;
	
	@Column(unique = false, nullable = true, length=10)
	private String cylNearLeft;
	
	@Column(unique = false, nullable = true, length=10)
	private String cylNearRight;
	
	@Column(unique = false, nullable = true, length=3)
	private Integer axisNearLeft;
	
	@Column(unique = false, nullable = true, length=3)
	private Integer axisNearRight;
	
	@Column(unique = false, nullable = true, length=10)
	private String vaNearLeft;
	
	@Column(unique = false, nullable = true, length=10)
	private String vaNearRight;
	
	@Column(unique = false, nullable = true, length=30)
	private String remarksRight;
	
	@Column(unique = false, nullable = true, length=200)
	private String remarksRightDesc;
	
	@Column(unique = false, nullable = true, length=30)
	private String remarksLeft;
	
	@Column(unique = false, nullable = true, length=200)
	private String remarksLeftDesc;
	
	// Findings Details
	
	@Column(unique = false, nullable = true, length=200)
	private String anteriorSeg;
	
	@Column(unique = false, nullable = true, length=200 )
	private String posteriorSeg;
	
	@Column(unique = false, nullable = true)
	private Integer iopRight;
	
	@Column(unique = false, nullable = true)
	private Integer iopLeft;
	
	@Column(unique = false, nullable = true, length=200)
	private String complaints;
	
	// A-Scan Details
	
	@Column(unique = false, nullable = true)
	private Float keraK1;
	
	@Column(unique = false, nullable = true)
	private Float keraK2;
	
	@Column(unique = false, nullable = true)
	private Float axialLength;
	
	@Column(unique = false, nullable = true)
	private Float iolPower;
	
	@Column(unique = false, nullable = true, length=200)
	private String surgicalPlan;
	
	// Discharge Summary Details
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(unique = false, nullable = true)
	private Date admissionDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(unique = false, nullable = true)
	private Date surgeryDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(unique = false, nullable = true)
	private Date dischargeDate;
	
	@Column(unique = false, nullable = true, length=200)
	private String diagnosis;
	
	@Column(unique = false, nullable = true, length=200)
	private String surgicalProc;
	
	@Column(unique = false, nullable = true, length=50)
	private String surgeon;
	
	@Column(unique = false, nullable = true, length=50)
	private String anaesthetist;
	
	@Column(unique = false, nullable = true, length=200)
	private String investigations;
	
	@Column(unique = false, nullable = true)
	private Date followUp;
	
	/*@Column(unique = false, nullable = true, length=3 )
	private Integer followUp;
	*/
	
	//Additional Fields
	
	/**
	 * @author omini007
	 * @category Basic fields for a clinic
	 * @since 2017-11-14
	 */
	
	@Column(unique = false, nullable = true, length=10)
	private String visionWithoutGlassRight;
	
	@Column(unique = false, nullable = true, length=20)
	private String visionWOGlassRightDesc;
	
	@Column(unique = false, nullable = true, length=10)
	private String visionWithoutGlassLeft;
	
	@Column(unique = false, nullable = true, length=20)
	private String visionWOGlassLeftDesc;
	
	@Column(unique = false, nullable = true, length=10)
	private String visionWithGlassRight;
	
	@Column(unique = false, nullable = true, length=20)
	private String visionWGlassRightDesc;
	
	@Column(unique = false, nullable = true, length=10)
	private String visionWithGlassLeft;
	
	@Column(unique = false, nullable = true, length=20)
	private String visionWGlassLeftDesc;
	
	@Column(unique = false, nullable = true, length=30)
	private String lensRight;
	
	@Column(unique = false, nullable = true, length=30)
	private String lensRightDesc;
	
	@Column(unique = false, nullable = true, length=30)
	private String lensLeft;
	
	@Column(unique = false, nullable = true, length=30)
	private String lensLeftDesc;
	                                                
	@Column(unique = false, nullable = true, length=30)
	private String corneaRight;  
	
	@Column(unique = false, nullable = true, length=30)
	private String corneaRightDesc;
	                                                
	@Column(unique = false, nullable = true, length=30)
	private String corneaLeft;                      
	
	@Column(unique = false, nullable = true, length=30)
	private String corneaLeftDesc;
	
	@Column(unique = false, nullable = true, length=30)
	private String pupilRight;
	
	@Column(unique = false, nullable = true, length=30)
	private String pupilRightDesc;
	                                                
	@Column(unique = false, nullable = true, length=30)
	private String pupilLeft;
	
	@Column(unique = false, nullable = true, length=30)
	private String pupilLeftDesc;
	
	@Column(unique = false, nullable = true, length=30)
	private String irisRight;
	
	@Column(unique = false, nullable = true, length=30)
	private String irisRightDesc;
	
	@Column(unique = false, nullable = true, length=30)
	private String irisLeft;
	
	@Column(unique = false, nullable = true, length=30)
	private String irisLeftDesc;
	
	@Column(unique = false, nullable = true, length=30)
	private String antChamberRight;
	
	@Column(unique = false, nullable = true, length=30)
	private String antChamberRightDesc;
	
	@Column(unique = false, nullable = true, length=30)
	private String antChamberLeft;
	
	@Column(unique = false, nullable = true, length=30)
	private String antChamberLeftDesc;
	
	@Column(unique = false, nullable = true, length=50)
	private String fundusRight;
	
	@Column(unique = false, nullable = true, length=50)
	private String fundusLeft;
	
	@Column(unique = false, nullable = true, length=10)
	private String swellingRight;
	
	@Column(unique = false, nullable = true, length=10)
	private String swellingLeft;
	
	@Column(unique = false, nullable = true, length=10)
	private String rednessRight;
	
	@Column(unique = false, nullable = true, length=10)
	private String rednessLeft;
	
	@Column(unique = false, nullable = true, length=10)
	private String conjRednessRight;
	
	@Column(unique = false, nullable = true, length=10)
	private String conjRednessLeft;
	
	@Column(unique = false, nullable = true, length=10)
	private String conjTearRight;
	
	@Column(unique = false, nullable = true, length=10)
	private String conjTearLeft;
	
	@Column(unique = false, nullable = true, length=30)
	private String ocularSurgeryRight;
	
	@Column(unique = false, nullable = true, length=30)
	private String ocularSurgeryLeft;
	
	@Column(unique = false, nullable = true, length=30)
	private String sacPBl;
	
	@Column(unique = false, nullable = true, length=30)
	private String urine;
	
	@Column(unique = false, nullable = true)
	private Float bloodPressureVal1;
	
	@Column(unique = false, nullable = true)
	private Float bloodPressureVal2;
	        
	@Column(unique = false, nullable = true)
	private Float bloodSugarFasting;
	
	@Column(unique = false, nullable = true)
	private Float bloodSugarPp;
	
	/**
	 * END
	 */
	
	@OneToMany( cascade = CascadeType.ALL , fetch = FetchType.EAGER, orphanRemoval = true )
	@JoinColumn( name = "visitId", referencedColumnName = "visitId")
	private List<DrugDetailsPOJO> drugs = new ArrayList<DrugDetailsPOJO>();

	@Transient
	private String searchText;
	
	@Transient
	private String searchFilter;
	
	@Transient
	private String searchDateFrom;
	
	@Transient
	private String searchDateTo;
	
}
