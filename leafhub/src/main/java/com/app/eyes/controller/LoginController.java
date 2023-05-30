package com.app.eyes.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.eyes.model.ApiResponse;
import com.app.eyes.model.UserLogin;
import com.app.eyes.service.SecQuestionService;
import com.app.eyes.service.UserService;

@RestController
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(RestController.class);
	
	@Autowired 
	UserService userService;
	
	@Autowired
	SecQuestionService passwordService;
	
	
	@PostMapping("/api/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody UserLogin user) {
		ApiResponse response = new ApiResponse();
        try {

            int a = userService.validateLogin(user.getUserName(),user.getPassword());

            if (a == 0) {
                response.setMessage("Login Failed");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);            }
            else {
            	 logger.info("Successfully Logged in");
                 response.setMessage("Successfully Logged in");
                 return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            logger.error("Error occurred during login process", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
	
	

	
}
