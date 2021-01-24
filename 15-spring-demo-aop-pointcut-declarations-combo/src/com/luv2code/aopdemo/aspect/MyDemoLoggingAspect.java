package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {	
	
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))") // match any method with this package
	private void forDaoPackage() {}
	
	// create pointcut for getter methods
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))") 
	private void getter() {}

	// create pointcut for setter methods
	@Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))") 
	private void setter() {}

	// create pointcut: includes package ... exclude getter/setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() {}
	
	@Before("forDaoPackageNoGetterSetter()") 
	public void beforeAddAccountAdvice() {
		System.out.println("\n =====> Executing @Before advice on addAccount() ");
	}
	
	@Before("forDaoPackageNoGetterSetter()") 
	public void performApiAnalytics() {
		System.out.println("\n =====> Perform Api Analytics() ");
	}

}
