package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;


@Controller
public class MyController {

	@RequestMapping("/")
	public String getFirstPage() {
		return "home.html";
	}
	
	@RequestMapping("/signin")
	public String getLogin() {
		return "login.html";
	}
	
//	Method 1: Using Http Session
	@RequestMapping("/verify")
	public String verifyUser(String user, String pwd, HttpSession session) {
		session.setAttribute("uname",user);
		if(user.equalsIgnoreCase(pwd)) {
			return "welcome.jsp";
		}
		else {
			return "failure.jsp";
		}
	}
	
//	Method 2: Using ModelAndView/ModelMap
	@RequestMapping("/verifymv")
	public ModelAndView verifyUserMv(String user, String password) {
		ModelAndView mv = new ModelAndView();
		if(user.equalsIgnoreCase(password)) {
			mv.addObject("uname",user);
			mv.setViewName("welcome.jsp");
		}
		else {
			mv.setViewName("failure.jsp");
		}
		return mv;
	}
}
