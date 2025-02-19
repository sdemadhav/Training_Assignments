package com.example.springRestApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springRestApp.model.Person;

@RestController
public class ParameterTypesController {

	@GetMapping("/query")
	public String queryParamDemo(@RequestParam String name, @RequestParam int age)
	{
		return "Welcome "+name+" and you are "+age+" years old !?!";
	}
	
	@GetMapping("/path/{name}/{age}")
	public String pathParamDemo(@PathVariable String name, @PathVariable int age)
	{
		return "Welcome "+name+" and you are "+age+" years old !?!";
	}
	
	@GetMapping("/body")
	public String bodyParamDemo(@RequestBody Person p)
	{
		System.out.println("Welcome, my friend "+p.getName()+" , you are "+ p.getAge()+" years old !");
		return new String();
	}
	
	@GetMapping(path = "/persons",produces="application/xml") //default is application/json, since java default is xml we have to add a xml parser dependency like jackson xml dataformat
	public List<Person> printList() {
		List<Person> ll = new ArrayList<Person>();
		Person p = new Person("Maddy",21);
		Person q = new Person("Sannzzz",23);
		Person r = new Person("Karan1812",1812);
		Person s = new Person("Jonty rhodes",25);
		
		ll.add(p); ll.add(q); ll.add(r); ll.add(s);
		return ll;		
	}
}
