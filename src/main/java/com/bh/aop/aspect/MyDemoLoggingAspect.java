package com.bh.aop.aspect;

import com.bh.aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	@Around("execution(* com.bh.aop.service.*.getFortune(..))")
	public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

		String method = proceedingJoinPoint.getSignature().toShortString();

		System.out.println("\n=====>> executing @Around on method " + method);

		long begin = System.currentTimeMillis();

		Object result = proceedingJoinPoint.proceed();

		long end = System.currentTimeMillis();

		long duration = end - begin;

		System.out.println("\n===>> Duration: " + duration / 1000 + "seconds ");

		return result;
	}

	@After("execution(* com.bh.aop.dao.AccountDAO.findAccounts(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

		String method = joinPoint.getSignature().toShortString();

		System.out.println("\n=====>> executing finally on method " + method);
	}

	@AfterThrowing(
			pointcut = "execution(* com.bh.aop.dao.AccountDAO.findAccounts(..))",
			throwing = "exc"
	)
	public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc) {

		String method = joinPoint.getSignature().toShortString();

		System.out.println("\n=====>> the exception is " + exc);
	}

	@AfterReturning(
			pointcut = "execution(* com.bh.aop.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

		String method = joinPoint.getSignature().toShortString();

		System.out.println("\n=====>> Executing @AfterReturning on method " + method);

		System.out.println("\n ===>> result is " + result);

		convertAccountNamesToUpperCase(result);

		System.out.println(result);
	}

	private void convertAccountNamesToUpperCase(List<Account> result) {

		for (Account account : result) {

			String upperName = account.getName().toUpperCase();
			account.setName(upperName);
		}
	}

	@Before("com.bh.aop.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n=====>>> Executing @Before advice on method");

		MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

		System.out.println("Method " + methodSignature);

		Object[] args = theJoinPoint.getArgs();

		for (Object tempArg : args) {

			System.out.println(tempArg);

			if (tempArg instanceof Account) {

				Account account = (Account) tempArg;

				System.out.println(account.getLevel());
			}
		}
	}
}