package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Contains aspect for "api analytics" (academic exercise, no real impl)
 */
@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {

    //Nonsense practice, just playing
    @Before("com.luv2code.aopdemo.aspect.AopExpressions.daoPackageExcludeGettersSetters()")
    public void performApiAnalytics(){
        System.out.println("\n=====>>> Performing API analytics");
    }

}
