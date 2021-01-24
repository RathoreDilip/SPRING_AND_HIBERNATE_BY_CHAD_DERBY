package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	// this is where we add all of our related advices for logging
	
	// let's start with an @Before advice  
	
	//@Before("execution(public void addAccount())")
	//@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
	//@Before("execution(public void add*())")
	//@Before("execution(void add*())")
	//@Before("execution(* add*(Account))") // need fully qualified name
	//@Before("execution(* add*(com.luv2code.aopdemo.Account))")   // match only Account parameter type
	//@Before("execution(* add*(com.luv2code.aopdemo.Account,..))")   // match only Account,one more parameter type
	//@Before("execution(* add*(..))") 
	@Before("execution(* com.luv2code.aopdemo.dao.*.*(..))") // match any method with this package
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n =====> Executing @Before advice on addAccount() ");
		
	}

}
