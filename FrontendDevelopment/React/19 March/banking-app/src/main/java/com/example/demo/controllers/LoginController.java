package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Credentials;
import com.example.demo.services.ValidateService;

@RestController
public class LoginController {
	
	@Autowired
	ValidateService validateService;
	
	@PostMapping("/login")
	public boolean validateUser(@RequestBody Credentials credentials){
		return validateService.validateLogin(credentials);
	}

}
