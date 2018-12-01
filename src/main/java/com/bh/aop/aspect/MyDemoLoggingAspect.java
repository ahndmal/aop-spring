package com.bh.aop.aspect;

import com.bh.aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

	@AfterReturning(
			pointcut = "execution(* com.bh.aop.dao.AccountDAO.findAccounts(..))",
			returning = "result")
	public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

		String method = joinPoint.getSignature().toShortString();

		System.out.println("\n=====>> Executing @AfterReturning on method " + method);

		System.out.println("\n ===>> result is " + result);

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