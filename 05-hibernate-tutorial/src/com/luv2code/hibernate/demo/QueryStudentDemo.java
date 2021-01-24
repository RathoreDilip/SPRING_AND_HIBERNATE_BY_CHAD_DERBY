package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// query students
			List<Student> theStudents = session.createQuery(" from Student").getResultList();

			// display students
			displayStudents(theStudents);

			// query students: lastName='Rathore'
			theStudents = session.createQuery(" from Student s where s.lastName='Rathore'").getResultList();

			// display students
			System.out.println("\n\n Students who have last name of 'Rathore'");
			displayStudents(theStudents);

			// query students: lastName='Rathore' or firstName='Tarang'
			theStudents = session.createQuery(" from Student s where s.lastName='Rathore' OR s.firstName='Tarang'")
					.getResultList();

			// display students
			System.out.println("\n\n Students who have last name of 'Rathore' or first name of 'Tarang' ");
			displayStudents(theStudents);

			// query students: email ends with @gmail.com
			theStudents = session.createQuery(" from Student s where s.email like '%@gmail.com' ")
					.getResultList();

			// display students
			System.out.println("\n\n Students who have email ends with '@gmail.com' ");
			displayStudents(theStudents);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done..");

		} finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
