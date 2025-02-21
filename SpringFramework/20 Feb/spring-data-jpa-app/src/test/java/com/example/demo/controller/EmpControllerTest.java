package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.controllers.EmpController;
import com.example.demo.entities.Employee;
import com.example.demo.services.EmployeeService;

@WebMvcTest(controllers = EmpController.class)
public class EmpControllerTest {
	
	@MockitoBean
	private EmployeeService empService;
	
	@Autowired
	private  MockMvc mockMvc;
	
	@Test
	public void testGetEmployees() {
		try {
			//Check whether the /employees rest url is working or in other terms is giving 200 Status code or not.
			mockMvc.perform(MockMvcRequestBuilders.get("/employees")).andExpect(MockMvcResultMatchers.status().is(200));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testGetEmployeeBelowAge() {
		try {
			List<Employee> list = new ArrayList<Employee>();
			list.add(new Employee());
			Mockito.when(empService.getEmployeeBelow(25)).thenReturn(list);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
