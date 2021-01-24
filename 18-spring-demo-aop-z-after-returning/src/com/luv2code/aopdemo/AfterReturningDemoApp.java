package com.luv2code.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class AfterReturningDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO =context.getBean("accountDAO",AccountDAO.class);
		
		// call method to find the accounts
		List<Account> findAccounts	= theAccountDAO.findAccount();
		
		// display the accounts
		System.out.println("\n\n Main Program : AfterReturningDemoApp");
		System.out.println("----");
		
		System.out.println(findAccounts);
		
		System.out.println("\n");
		
		// close the context
		context.close();
		
	}

}
