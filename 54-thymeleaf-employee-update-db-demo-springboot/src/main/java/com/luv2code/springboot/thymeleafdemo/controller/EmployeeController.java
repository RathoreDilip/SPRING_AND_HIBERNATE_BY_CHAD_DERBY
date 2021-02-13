package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Employee theEmployee=new Employee();
		
		theModel.addAttribute("employee", theEmployee);
		
		return "employees/employee-form";
		
	}
	
	@GetMapping("/showFormForUpdate")
	public String updateEmployee(@RequestParam("employeeId") int theId,Model model) {
		
		// get the employee form for service
		Employee employee=employeeService.findById(theId);
		
		// set employee as a model attribute to pre-population 
		model.addAttribute("employee",employee);
		
		// send over to our form
		return "employees/employee-form";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("employeeId") int theId,Model model) {
		
		// delete the employee
		employeeService.deleteById(theId);
		
		// redirect to employee/list
		return "redirect:/employees/list";
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {
		
		// save the employee
		employeeService.save(theEmployee);
		
		// use a redirect to prevent duplicate submissions
		
		return "redirect:/employees/list";
		
	}
	
	
	
}
