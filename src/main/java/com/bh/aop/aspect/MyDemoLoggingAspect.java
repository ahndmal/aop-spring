package com.bh.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

	@Pointcut("execution(* com.bh.aop.dao.*.*(..))")
	private void forDaoPackage() {}

	@Pointcut("execution(* com.bh.aop.dao.*.get*(..))")
	private void getter() {}

	@Pointcut("execution(* com.bh.aop.dao.*.set*(..))")
	private void setter() {}

	@Pointcut("forDaoPackage() && !(getter() || setter())")
	private void forDaoPackageNoGetterSetter() {}
	
	@Before("forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice() {		
		System.out.println("\n=====>>> Executing @Before advice on method");		
	}
	
	@Before("forDaoPackage()")
	public void performApiAnalytics() {
		System.out.println("\n=====>>> Performing API analytics");		
	}

	@Before("forDaoPackage()")
	public void logToCloudAsync() {
		System.out.println("\n=====>>> Logging to Cloud in async");
	}
}