package com.app.eyes.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Table(name="DrugDetails")
@Component
public class DrugDetailsPOJO {

	@Id
	@GeneratedValue
	private Integer drugId;
	
	/*@ManyToOne ( cascade=CascadeType.ALL, fetch = FetchType.LAZY ) 
	@JoinColumn( name = "visitId")
	private VisitDetailsPOJO visit;
	*/
	@Column(unique = false, nullable = true, length=20)
	private String drugType;
	
	@Column(unique = false, nullable = true, length=50)
	private String drugName;
	
	@Column(unique = false, nullable = true, length=20)
	private String quantity;
	
	@Column(unique = false, nullable = true, length=20)
	private String frequency;
	
	@Column(unique = false, nullable = true, length=3)
	private Integer duration;
	
	@Column(unique = false, nullable = true, length=10)
	private String durationType;
}
