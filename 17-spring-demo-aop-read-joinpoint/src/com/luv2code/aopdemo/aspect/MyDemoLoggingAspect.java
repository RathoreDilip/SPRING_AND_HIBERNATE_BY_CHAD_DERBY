package com.luv2code.aopdemo.aspect;

import java.util.Iterator;

import org.aspectj.lang.JoinPoint;
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
