package com.luv2code.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springboot.cruddemo.entitiy.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
	
@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeService employeeService;
	// quick and dirty : inject employee dao (use constructor injection)
	
	@Autowired
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService=theEmployeeService;
	}
	
	// expose "/employess" and return list of employess
	@RequestMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	// add mapping for GET /employees/{employeeId}
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		
		Employee thEmployee=employeeService.findById(employeeId);
		
		if(thEmployee==null)
			throw new RuntimeException("Employee id not found - "+employeeId);
		
		return thEmployee;
		
	}
	
	// add mapping for POST /employees - add new employees
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee thEmployee) {
		
		// also just in case they pass an id in JSON ... set id to 0
		// this is to force a save of new item... instead of update
		
		thEmployee.setId(0);
		
		employeeService.save(thEmployee);
		
		return thEmployee;
	}
	
	// add mapping for PUT / employees - update existing employee
	
	@PutMapping("employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		
		employeeService.save(theEmployee);
		
		return theEmployee;
	}
	
	// add mapping for DELETE /employees/{employeeId} - delete employee
	
	@DeleteMapping("employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		
		Employee tempEmployee=employeeService.findById(employeeId);
		
		// throw exception if null
		if(tempEmployee==null)
			throw new RuntimeException("Empolyee id not found - "+employeeId);
		
		employeeService.deleteById(employeeId);
		
		return "Delete employee id - "+employeeId;
	}
	
	
	
	
	
}
