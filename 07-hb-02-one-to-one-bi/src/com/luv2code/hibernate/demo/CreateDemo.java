package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.InstrcutorDetail;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

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
			
			// crate the objects 
			Instructor tempIntructor=new Instructor("Dilip", "Rathore", "dilip@gmail.com");
			
			InstrcutorDetail tempInstructorDetail=new InstrcutorDetail("http://www.dilip.com/youtube", "Luv 2 code!!!");
			
			// associate the objects 
			tempIntructor.setInstrcutorDetail(tempInstructorDetail);
			
			// start a transaction
			session.beginTransaction();

			// save the instructor
			//
			// NOTE : this will ALSO save the details objects 
			// because of CascadeType.ALL
			//
			
			System.out.println("saving instrctor : "+tempIntructor);
			session.save(tempIntructor);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done..");
			
		} finally {
			factory.close();
		}
		
		
		
	}

}
