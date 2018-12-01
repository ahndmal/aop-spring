package com.bh.aop.aspect;

import com.bh.aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());

	@Around("execution(* com.bh.aop.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		String method = proceedingJoinPoint.getSignature().toShortString();

		myLogger.info("\n=====>> executing @Around on method " + method);

		long begin = System.currentTimeMillis();

		Object result = null;

		try {
			result = proceedingJoinPoint.proceed();

		} catch (Exception e) {

			myLogger.warning(e.getMessage());

			throw e; // throwing the exception !!!
		}

		long end = System.currentTimeMillis();

		long duration = end - begin;

		myLogger.info("\n===>> Duration: " + duration / 1000 + "seconds ");

		return result;
	}

	@After("execution(* com.bh.aop.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

		String method = joinPoint.getSignature().toShortString();

		myLogger.info("\n=====>> executing finally on method " + method);
	}

	@AfterThrowing(
			pointcut = "execution(* com.bh.aop.dao.AccountDAO.findAccounts(..))",
			throwing = "exc"
	)
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc) {

		String method = joinPoint.getSignature().toShortString();

		myLogger.info("\n=====>> the exception is " + exc);
	}

	@AfterReturning(
			pointcut = "execution(* com.bh.aop.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

		String method = joinPoint.getSignature().toShortString();

		myLogger.info("\n=====>> Executing @AfterReturning on method " + method);

		myLogger.info("\n ===>> result is " + result);

		convertAccountNamesToUpperCase(result);
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {

		for (Account account : result) {

			String upperName = account.getName().toUpperCase();
			account.setName(upperName);
		}
	}

	@Before("com.bh.aop.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		myLogger.info("\n=====>>> Executing @Before advice on method");

		MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

		myLogger.info("Method " + methodSignature);

		Object[] args = theJoinPoint.getArgs();

		for (Object tempArg : args) {

			myLogger.info(tempArg.toString());

			if (tempArg instanceof Account) {

				Account account = (Account) tempArg;

				myLogger.info(account.getLevel());
			}
		}
	}
}