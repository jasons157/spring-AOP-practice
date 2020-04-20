package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * This class contains pointcut declarations so that other aspects
 * may access them.
 */
@Aspect
public class AopExpressions {
    /**
     * This reads as follows:
     *      com.luv2code.aopdemo.dao.ANY_CLASS.ANY_METHOD(any # args)
     *      with any modifier and return type
     * This results in a match from anything in a specified package
     * Can be addressed by calling forDaoPackage()
     */
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.*(..))")
    public void forDaoPackage(){}

    //create pointcut for getter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.get*(..))")
    public void getter(){}

    //create pointcut for setter methods
    @Pointcut("execution(* com.luv2code.aopdemo.dao.*.set*(..))")
    public void setter(){}

    //create pointcut to include package AND exclude getters/setters
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void daoPackageExcludeGettersSetters(){}
}
