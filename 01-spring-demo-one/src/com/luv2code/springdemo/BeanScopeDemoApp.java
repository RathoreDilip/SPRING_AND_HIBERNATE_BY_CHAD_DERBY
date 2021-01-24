package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		// load the spring configuration files
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		
		// retrieve bean from spring container
		Coach theCoach=context.getBean("myCoach",Coach.class);
		
		Coach alphaCoach=context.getBean("myCoach",Coach.class);
		
		// check if they are same
		boolean result=(theCoach == alphaCoach);
		
		// print out the results // by defualt singletone scope // now prototype scope(change in configuration file) 
		System.out.println("\nPointing to the same object :: "+result);
		
		System.out.println("\nMemory location for theCoach :: "+theCoach);
		
		System.out.println("\nMemory location for alphaCoach :: "+alphaCoach);
		
		// close the context
		context.close();
		
	}

}
