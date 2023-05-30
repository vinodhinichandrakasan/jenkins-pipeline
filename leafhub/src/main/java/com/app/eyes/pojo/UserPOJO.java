package com.app.eyes.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Table(name="Users")
@Component
public class UserPOJO {
	
	@Column(name = "userName", unique = true, nullable = false, length=20)
	@Id
	private String userName;
	
	@Column(length=30)
	@NotEmpty
	private String name;
	
	@Column(length=16)
	@NotEmpty
	private String password;
	
	@Column(length=60)
	@Email
	private String email;
	
	@Transient
	private String cnfPassword;
	
	@Column(length=100)
	private String secQuest;
	
	@Column(length=30)
	@NotEmpty
	private String answer;
	
	@Column(length=15)
	@NotEmpty
	private String role;
	
	private static final Logger logger = Logger.getLogger(UserPOJO.class);
	public UserPOJO() {
		logger.info("Default Constructor UserPOJO");
	}
	
	public UserPOJO(String userName, String name, String password, String email) {
		super();
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
}
