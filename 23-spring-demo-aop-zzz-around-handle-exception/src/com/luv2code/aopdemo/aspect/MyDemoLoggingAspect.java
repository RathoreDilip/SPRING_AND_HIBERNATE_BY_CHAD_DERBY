package com.luv2code.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
	
	private Logger myLogger=Logger.getLogger(getClass().getName());
	
	@Around("execution( * com.luv2code.aopdemo.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		String method = proceedingJoinPoint.getSignature().toShortString();
		myLogger.info("\n====>>> Executing @Around on method: "+method);
		
		// get begin timestamp
		long begin=System.currentTimeMillis();	
		
		// now, let's execute the method
		Object result=null; 
		
		try {
			result=proceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception
			myLogger.warning(e.getMessage());
			
//			// give user a custom message
//			result="Major accident! But no worries, your private AOP helicopter is on the way!";
			
			// rethrow exception
			throw e;
		}
		
		// get end timestamp
		long end=System.currentTimeMillis();
		
		// compute duration and display it
		long duration=end-begin;
		
		myLogger.info("\n ====>>> Duration: "+(duration / 1000.0)+" seconds..");
		
		return result;
	}
	
	@After("execution( * com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))")
	public void afterFinallyFindAccountAdvice(JoinPoint joinPoint) {
		
		// print out the which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("\n====>>> Executing @After (Finally) on method: "+method);
		
	}
	
	@AfterThrowing(pointcut="execution( * com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))",throwing="theException")
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint,Throwable theException) {
		
		// print out the which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("\n====>>> Executing @AfterThrowing on method: "+method);
		
		// log the exception
		myLogger.info("\n====>>> The Exception is : "+theException);
		
	}
	
	// add a new advice for @AfterReturning on the findAccounts methods
	@AfterReturning(pointcut="execution( * com.luv2code.aopdemo.dao.AccountDAO.findAccount(..))",returning="result")
	public void afterReturningFindAccountAdvice(JoinPoint joinPoint,List<Account> result) {
		
		// print out which method we are advising on
		String method = joinPoint.getSignature().toShortString();
		myLogger.info("\n====>>> Executing @AfterReturning on method: "+method);
		
		// print out the results of the method call
		myLogger.info("\n====>>> result is : "+result);
		
		// let's post-process the data ... let's modify it :-)
		
		
		// convert the account names to upper case
		convertAccountNamesToUpperCase(result);
		
		myLogger.info("\n====>>> result is : "+result);
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
		myLogger.info("\n =====> Executing @Before advice on addAccount() ");
		
		// display the method signature
		MethodSignature methodSignature=(MethodSignature) theJoinPoint.getSignature();
		
		myLogger.info("Method : "+methodSignature);
		
		// display method arguments
		
		// get args
		Object[] args=theJoinPoint.getArgs();
		
		// loop through args
		for (Object object : args) {
			myLogger.info(object.toString());
			
			if(object instanceof Account) {
				// downcast and print Account specific stuff
				Account account=(Account)object;
				
				myLogger.info("Account name : "+account.getName());
				myLogger.info("Account level :"+account.getLevel());
			}
		}
		
	}

}
