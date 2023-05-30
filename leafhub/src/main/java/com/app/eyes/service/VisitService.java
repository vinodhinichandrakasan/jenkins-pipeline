package com.app.eyes.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.eyes.pojo.VisitDetailsPOJO;

public interface VisitService extends CrudRepository<VisitDetailsPOJO, Integer>{
	
	VisitDetailsPOJO addVisit(VisitDetailsPOJO visitDetails);
	
	@Query(value="from VisitDetailsPOJO v where (lower(v.patient.firstName) like %:name%) or (lower(v.patient.lastName) like %:name%) "
			+ "order by v.visitId desc")
	List<VisitDetailsPOJO> findVisitByName(@Param("name") String name);
	
	@Query(value="from VisitDetailsPOJO v where v.visitDate between to_date(:fromDate) and to_date(:toDate) + (86399/86400)"
			+ "order by v.visitId desc")
	List<VisitDetailsPOJO> findVisitByDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate);
	
	void clearForm(VisitDetailsPOJO visitDetails);
}
