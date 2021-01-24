package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.InstrcutorDetail;
import com.luv2code.hibernate.demo.entity.Instructor;

public class EagerLazyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstrcutorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);

			System.out.println("luv2code Instructor :: " + tempInstructor);

			 // get courses for the instructor
			// System.out.println("luv2code Courses :: "+tempInstructor.getCourses());

			// commit transaction
			session.getTransaction().commit();

			// close the session
			session.close();
			
			// since courses are lazy loaded.. this should fail..
			// option 1 : solve using getter method while session is open
			
			// get courses for the instructor
			System.out.println("luv2code Courses :: " + tempInstructor.getCourses());

			System.out.println("luv2code Done..");

		} finally {
			// add clean up code
			session.close();

			factory.close();
		}

	}

}