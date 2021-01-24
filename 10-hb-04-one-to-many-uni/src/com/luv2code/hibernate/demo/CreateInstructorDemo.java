package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.InstrcutorDetail;
import com.luv2code.hibernate.demo.entity.Instructor;

public class CreateInstructorDemo {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory=new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstrcutorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
									
		// create session
		Session session=factory.getCurrentSession();
		
		try {
			
			// crate the objects 
			Instructor tempIntructor=new Instructor("Raghu", "Malhotra", "raghu@gmail.com");
			
			InstrcutorDetail tempInstructorDetail=new InstrcutorDetail("http://www.raghu.com/youtube", "Alcohol");
			
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
			// add clean up code
			session.close();
			factory.close();
		}
		
		
		
	}

}
