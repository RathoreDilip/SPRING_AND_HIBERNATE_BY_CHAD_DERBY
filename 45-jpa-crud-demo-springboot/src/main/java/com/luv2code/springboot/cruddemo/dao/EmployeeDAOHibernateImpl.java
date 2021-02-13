package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springboot.cruddemo.entitiy.Employee;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	// define field for entitymanager
	private EntityManager entityManager;	
	
	// set up construction injection
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager theEntityManager) {
		entityManager=theEntityManager;
	}
	
	public List<Employee> findAll() {
		
		// get the current hibernate session
		Session currentSession=entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> theQuery=currentSession.createQuery("from Employee",Employee.class);
		
		// execute query and get result list
		List<Employee> employees=theQuery.getResultList();
		
		// return the results
		return employees;
	}

	public Employee findById(int theId) {
		
		// get the current hibernate session
		Session session=entityManager.unwrap(Session.class);
		
		// get the employee
		Employee employee=session.get(Employee.class, theId);
		
		// return the employee
		return employee;
	}

	public void save(Employee employee) {
		
		// get the current hibernate session
		Session session=entityManager.unwrap(Session.class);
		
		// save employees
		session.saveOrUpdate(employee);
		
	}

	public void deleteById(int theId) {
		
		// get the current hibernate session
		Session session=entityManager.unwrap(Session.class);
		
		// delete object with primary key
		Query theQuery=session.createQuery("delete from Employee where id=:employeeId ");
		theQuery.setParameter("employeeId", theId);
			
		theQuery.executeUpdate();
		
	}

	
	
}
