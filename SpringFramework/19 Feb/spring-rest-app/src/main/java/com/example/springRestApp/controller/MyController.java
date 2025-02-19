package com.example.springRestApp.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@GetMapping(path="/",produces = "text/html") //default is html other options are text/plain and text/xml 
	public String abc()
	{
		return "<html><body><h3>Hey There This is a HTML response from <br></br></h3></body></html>";
	}
	
	@PostMapping("/greet")
	public String xyz()
	{
		return "Hey there from /greet post method";
	}
	
	@PostMapping("/greet2")
	public String xyz1()
	{
		return "Hey there from /greet2 post method";
	}
	
	@DeleteMapping("/greet3")
	public String xyz2()
	{
		return "Hey there from /greet3 delete method";
	}
	
	@PutMapping("/greet4")
	public String xyz3()
	{
		return "Hey there from /greet4 Put method";
	}
	
	@PatchMapping("/greet4")
	public String xyz4()
	{
		return "Hey there from /greet4 Patch method";
	}

}
