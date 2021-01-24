package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.InstrcutorDetail;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class AddCoursesForMarryDemo {

	public static void main(String[] args) {
		
		// create session factory 
		SessionFactory factory=new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstrcutorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
									
		// create session
		Session session=factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();

			// get the student marry from database
			int studentId=2;
			Student tempStudent=session.get(Student.class, studentId);		
			
			System.out.println("\nLoaded Student.."+tempStudent);
			System.out.println("Courses : "+tempStudent.getCourses());
			
			// create more courses
			Course tempCourse1=new Course("Spring & Hibernate Beginners..");
			Course tempCourse2=new Course("Full Stack : Angular and Spring Boot..");
			
			// add student to courses
			tempCourse1.addStudent(tempStudent);
			tempCourse2.addStudent(tempStudent);
			
			// save the courses
			System.out.println("\nSaving the Courses..");
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			
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
