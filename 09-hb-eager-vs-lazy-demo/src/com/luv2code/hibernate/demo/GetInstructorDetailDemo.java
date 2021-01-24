package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.InstrcutorDetail;
import com.luv2code.hibernate.demo.entity.Instructor;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory=new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstrcutorDetail.class)
									.buildSessionFactory();
									
		// create session
		Session session=factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get the instructor detail object
			int theId=2999;
			InstrcutorDetail tempInstructorDetail=session.get(InstrcutorDetail.class, theId);
			
			// print the instructor detail
			System.out.println("tempInstructorDetail :: "+tempInstructorDetail);
			
			// print the associated instructor 
			System.out.println("the associated instructor :: "+tempInstructorDetail.getInstructor());
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done..");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();

			factory.close();
		}
		
	}

}
