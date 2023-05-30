package com.app.eyes.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.eyes.model.SecQuestions;
import com.app.eyes.pojo.UserPOJO;
import com.app.eyes.service.SecQuestionService;
import com.app.eyes.service.UserService;
import com.app.eyes.utility.Utils;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserPOJO pojo;
	
	@Autowired
	SecQuestionService passwordService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/home" , method=RequestMethod.GET)
	public String showdashboard(Model model, HttpSession session) {
		
		logger.info("Dashboard Get"); 
		if( Utils.validateSession(session, "UserDetails") == false) {
			logger.info("Session Attribute is Null");
			logger.info("You are not logged in. Redirecting to Login Page");
			return "redirect:/";
		}
		else {
			session.getAttribute("UserDetails");
			model.addAttribute("userName", session.getAttribute("UserDetails").toString());
			logger.info("Current User : " + (session.getAttribute("UserDetails")).toString());
			return "dashboard";
		}
	}

	@RequestMapping(value="/logout" , method=RequestMethod.GET)
	public String logout(Model model, HttpSession session) {
		logger.info("Logout Get");
		try {
			session.invalidate();
			logger.info("Successfully Logged out");
			return "redirect:/";
		} catch (Exception e) {
			logger.info("Unable to Log out");
			e.printStackTrace();
			return "redirect:/dashboard/home";
		}
	}
	
	@RequestMapping(value="/registration", method=RequestMethod.GET)
	public String showRegister(Model model, HttpSession session) {
		logger.info("Register Get");
		
		if( Utils.validateSession(session, "UserDetails") == false) {
			logger.info("Session Attribute is Null");
			logger.info("You are not logged in. Redirecting to Login Page");
			return "redirect:/";
		}
		else if (session.getAttribute("userRole") != "receptionist") {
			session.getAttribute("UserDetails");
			model.addAttribute("userName", session.getAttribute("UserDetails").toString());
			
			model.addAttribute("newUser", pojo);
			List<SecQuestions> question		=	new ArrayList<>();
			model.addAttribute("question",question);
			List<SecQuestions> questions	=	passwordService.findAllQues();
			logger.info(questions);
			model.addAttribute("questions", questions);
			logger.info("Current User : " + (session.getAttribute("UserDetails")).toString());
			return "register";
		}
		else
			return "redirect:/dashboard";
	}
	
	@RequestMapping(value="/registration",method=RequestMethod.POST)
	public String register(@ModelAttribute("newUser")UserPOJO user,ModelMap modelMap) {
		logger.info("Register Post");
		Integer value = null;
		try {
			switch (userService.add(user)) {
			case 1:
				logger.info("Registration Successful, Redirecting to Login page");
				value	=	1;
				break;
			case 2:
				logger.info("Registration Failed, Could not add the new user");
				value	=	2;
				break;
			case 3:
				logger.info("Registration Failed, Passwords Do Not Match");
				value	=	3;
				break;
			case 4:
				logger.info("Registration Failed, Username already exists");
				value	=	4;
				break;
			default:
				break;
			}
			
		} catch (Exception e) {
			logger.info("Registration Failed, Exception Occured");
			value	=	0;
		}
		modelMap.addAttribute("signUpResult", value);
		return "redirect:/dashboard/registration?signUpResult=" + value.toString();
	}
}
