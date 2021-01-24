package com.luv2code.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SwimJavaSourceConfigDemoApp {

	public static void main(String[] args) {

		// read spring config file
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);

		// get the bean from spring container // explicitly
		// Coach theCoach = context.getBean("thatSillyCoach", Coach.class);

		// get the bean from spring container // implicit based on class
		SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);

		// call a method on the bean
		System.out.println(theCoach.getDailyWorkOut());

		// call method to get the daily fortune
		System.out.println(theCoach.getDailyFortune());
		
		// call our new swim coach methods.. has the props values injected
		System.out.println("email : "+theCoach.getEmail());
		System.out.println("team : "+theCoach.getTeam());

		// close the context
		context.close();

	}

}
