package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory=new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
									
		// create session
		Session session=factory.getCurrentSession();
		
		try {
		
			int studentId=6;
			
			// now get a new session and start transaction
			session=factory.getCurrentSession();
			
			session.beginTransaction();
			
			// retrieve student based on the id : primary key
			System.out.println("\n Getting student with id : "+studentId);
			
			Student myStudent=session.get(Student.class, studentId);
						
			System.out.println("Get Completed :: "+myStudent);
			
			// updating students 
			System.out.println("\n Deleting students..");

			session.delete(myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			// NEW CODE
			
			session=factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			System.out.println("delete student for id 4 ");
			session.createQuery("delete from Student s where s.id=5")
															.executeUpdate();
			
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			
			System.out.println("Done..");
			
		} finally {
			factory.close();
		}
		
		
		
	}

}
