package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entitiy.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}
	
	public List<Employee> findAll() {	
		
		// create a query
		Query theQuery=entityManager.createQuery("from Employee");
		
		// execute query and get result list
		List<Employee> employees=theQuery.getResultList();
		 
		// return the results
		return employees;
	}

	public Employee findById(int theId) {
		
		// get employee
		Employee theEmployee=entityManager.find(Employee.class, theId);
		
		// return employee
		return theEmployee;
	}

	public void save(Employee employee) {

		// save or update the employee
		Employee dbEmployee=entityManager.merge(employee);
		
		// update with id from db.. so we can generated id for save/insert 
		employee.setId(dbEmployee.getId());
		
	}

	public void deleteById(int theId) {
		
		// delete object with primary key
		
		Query theQuery=entityManager.createQuery(" delete from Employee where id=:employeeId ");
		
		theQuery.setParameter("employeeId", theId);
		
		theQuery.executeUpdate();
		
		
	}

}