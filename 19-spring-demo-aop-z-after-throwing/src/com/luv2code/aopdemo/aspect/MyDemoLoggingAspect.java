package com.luv2code.aopdemo.aspect;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {	
	
	@AfterThrowing(pointcut="execution( * com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))",throwing="theException")
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint,Throwable theException) {
		
		// print out the which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n====>>> Executing @AfterThrowing on method: "+method);
		
		// log the exception
		System.out.println("\n====>>> The Exception is : "+theException);
		
	}
	
	// add a new advice for @AfterReturning on the findAccounts methods
	@AfterReturning(pointcut="execution( * com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))",returning="result")
	public void afterReturningFindAccountAdvice(JoinPoint joinPoint,List<Account> result) {
		
		// print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		System.out.println("\n====>>> Executing @AfterReturning on method: "+method);
		
		// print out the results of the method call
		System.out.println("\n====>>> result is : "+result);
		
		// let's post-process the data ... let's modify it :-)
		
		
		// convert the account names to upper case
		convertAccountNamesToUpperCase(result);
		
		System.out.println("\n====>>> result is : "+result);
	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		// loop through accounts
		
		for (Account account : result) {
			// get uppercase version of name
			String theUpperName=account.getName().toUpperCase();
			
			// update the name of the account
			account.setName(theUpperName);
		}
	
	}

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpression.forDaoPackageNoGetterSetter()") 
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n =====> Executing @Before advice on addAccount() ");
		
		// display the method signature
		MethodSignature methodSignature=(MethodSignature) theJoinPoint.getSignature();
		
		System.out.println("Method : "+methodSignature);
		
		// display method arguments
		
		// get args
		Object[] args=theJoinPoint.getArgs();
		
		// loop through args
		for (Object object : args) {
			System.out.println(object);
			
			if(object instanceof Account) {
				// downcast and print Account specific stuff
				Account account=(Account)object;
				
				System.out.println("Account name : "+account.getName());
				System.out.println("Account level :"+account.getLevel());
			}
		}
		
	}

}
