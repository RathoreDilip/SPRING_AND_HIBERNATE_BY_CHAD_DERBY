package com.luv2code.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.thymeleafdemo.Entity.Employee;
import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
		employeeRepository=theEmployeeRepository;
	}
	
	public List<Employee> findAll() {
		return employeeRepository.findAllByOrderByLastNameAsc();
	}

	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);
		
		Employee employee=null;
		if(result.isPresent()) {
			employee=result.get();
		}else {
			// we didn't find the employee
			throw new RuntimeException("Did not find employee id - "+theId);
		}
		
		return employee;
	}
	
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

}
