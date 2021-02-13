package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.thymeleafdemo.Entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;


@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired    // optional if one constructor
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService theEmEmployeeService) {
		employeeService=theEmEmployeeService;
	}
	
	// add mapping for "/list"
	
	@GetMapping("/list")
	public String listEmployee(Model theModel) {
		
		// get employees from db
		List<Employee> employee=employeeService.findAll();
		
		// add the spring model
		
		theModel.addAttribute("employees", employee);
		
		return "list-employees";
	}
	
	
	
	
}
