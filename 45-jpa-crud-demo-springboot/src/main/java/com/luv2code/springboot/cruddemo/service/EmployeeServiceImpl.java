package com.luv2code.springboot.cruddemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.cruddemo.dao.EmployeeDAO;
import com.luv2code.springboot.cruddemo.entitiy.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
	
	@Autowired
	public EmployeeServiceImpl(@Qualifier("employeeDAOJpaImpl") EmployeeDAO theEmployeeDAO) {
		employeeDAO=theEmployeeDAO;
	}
	
	@Transactional
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Transactional
	public Employee findById(int theId) {
		return employeeDAO.findById(theId);
	}
	
	@Transactional
	public void save(Employee employee) {
		employeeDAO.save(employee);
	}

	@Transactional
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId);
	}

}
