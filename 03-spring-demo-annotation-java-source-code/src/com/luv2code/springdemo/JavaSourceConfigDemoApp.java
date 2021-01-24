package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaSourceConfigDemoApp {

	public static void main(String[] args) {

		// read spring config file
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);

		// get the bean from spring container // explicitly
		// Coach theCoach = context.getBean("thatSillyCoach", Coach.class);

		// get the bean from spring container // implicit based on class
		Coach theCoach = context.getBean("tennisCoach", Coach.class);

		// call a method on the bean
		System.out.println(theCoach.getDailyWorkOut());

		// call method to get the daily fortune
		System.out.println(theCoach.getDailyFortune());

		// close the context
		context.close();

	}

}
