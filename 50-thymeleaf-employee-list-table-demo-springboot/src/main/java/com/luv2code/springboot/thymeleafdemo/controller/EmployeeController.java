package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springboot.thymeleafdemo.model.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	// load employee data
		
	private List<Employee> employee;
	
	@PostConstruct
	private void loadData() {
		
		// create employees
		Employee emp1=new Employee(1, "Dilip", "Rathore", "dilip@gmail.com");
		Employee emp2=new Employee(2, "Deep", "Rathore", "deep@gmail.com");
		Employee emp3=new Employee(3, "Kabir", "Rathore", "kabir@gmail.com");
		
		// create list
		employee=new ArrayList<Employee>();
		
		// add list
		employee.add(emp1);
		employee.add(emp2);
		employee.add(emp3);
	}
	
	// add mapping for "/list"
	
	@GetMapping("/list")
	public String listEmployee(Model theModel) {
		
		theModel.addAttribute("employees", employee);
		
		return "list-employees";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
