package com.app.eyes.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.eyes.pojo.PatientDetailsPOJO;

@Repository
public interface PatientService extends CrudRepository<PatientDetailsPOJO, Integer>{
	
	String add(PatientDetailsPOJO patientDetails);
	
	@Query(value="from PatientDetailsPOJO p where (lower(p.firstName) like %:name%) or (lower(p.middleName) like %:name%) or (lower(p.lastName) like %:name%)")
	List<PatientDetailsPOJO> findPatientByName(@Param("name") String name);
	
	@Query(value="from PatientDetailsPOJO p where p.age=?1")
	List<PatientDetailsPOJO> findPatientByAge(Integer age);
	
	@Query(value="from PatientDetailsPOJO p")
	List<PatientDetailsPOJO> findAllPatients();
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update PatientDetailsPOJO p set p.title=?2, p.firstName=?3, p.middleName=?4, p.lastName=?5, "
			+ "p.gender=?6, p.email=?7, p.mobile=?8,  p.phoneNumber=?9 where p.patientId=?1")
	Integer editPatientDetails(Integer patientId, String title, String firstName, String middleName, String lastName, String gender, 
			String email, String mobile, String phoneNumber);
	
}
