package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger=Logger.getLogger(getClass().getName());
	
	// setup pointcut declarations
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPackage() {}
	
	// do the same for service and dao
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		
		// display we are calling
		String theMethod=joinPoint.getSignature().toShortString();
		myLogger.info("=====> in @Before : calling method : "+theMethod);
		
		// display the arguments to the method
		
		// get the arguments
		Object[] args=joinPoint.getArgs();
		
		// loop through and display args
		for (Object object : args) {
			myLogger.info("====> argument: "+object);
		}
		
	}
	
	// add @AfterReturning advice
	@AfterReturning(pointcut="forAppFlow()",returning="theResult")
	public void afterReturning(JoinPoint joinPoint,Object theResult) {
		
		// display method we are returning from
		String theMethod=joinPoint.getSignature().toShortString();
		myLogger.info("====> in @AfterReturning : calling method : "+theMethod);
		
		// display data returned
		myLogger.info("====> result : "+theResult);
		
	}
	
}
