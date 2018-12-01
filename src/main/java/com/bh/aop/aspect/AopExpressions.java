package com.bh.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpressions {

    @Pointcut("execution(* com.bh.aop.dao.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut("execution(* com.bh.aop.dao.*.get*(..))")
    public void getter() {}

    @Pointcut("execution(* com.bh.aop.dao.*.set*(..))")
    public void setter() {}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}

    
}
