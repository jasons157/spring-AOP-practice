package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This is where we add all of our related advice for logging
 */
@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    /**
     * Will output information after a method is called, regardless of success or failure
     * @param joinPoint
     */
    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint){

        //print method advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
    }

    /**
     * Will output information after an exception is thrown on its matching methods
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exception"
    )
    public void afterThrowingFindAccountsAdvice(
            JoinPoint joinPoint, Throwable exception) {

        //print method advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        //log exception
        System.out.println("\n=====>>> exception is: " + exception);
    }

    /**
     * Will output information after a method call is successfully returned
     * @param joinPoint
     * @param result
     */
    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(
            JoinPoint joinPoint, List<Account> result){

        //print out method we are advising on
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        //print out results of method call
        System.out.println("\n=====>>> result is: " + result);

        //modify data before it gets returned to calling method

        /**
         * WARNING!!!
         * Modifying the returned data can be really confusing
         * for people who don't know the AOP is intercepting and tampering with
         * the results. Avoid this unless dev team knows what to expect.
         */

        //convert names toUpper
        convertAccountNamesToUpperCase(result);
        System.out.println("\n=====>>> result is: " + result);


    }

    //utility method to update all the names to uppercase
    private void convertAccountNamesToUpperCase(List<Account> result) {
        for (Account tempAccount: result){
            tempAccount.setName(tempAccount.getName().toUpperCase());
        }
    }


    @Before("com.luv2code.aopdemo.aspect.AopExpressions.daoPackageExcludeGettersSetters()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        System.out.println("\n=====>>> Executing @Before advice on method");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        //display method args
        Object args[] = joinPoint.getArgs();
        for(Object tempArg:args){
            System.out.println(tempArg);

            if (tempArg instanceof Account){
                //downcast and print account specific stuff
                Account tempAccount = (Account) tempArg;

                System.out.println("Account name: " + tempAccount.getName());
                System.out.println("Account level: " + tempAccount.getLevel());

            }
        }
    }


    /**
     * This will match on any add() with any return type, any modifer, with any # of args (0..n)
     */
    /*@Before("execution(* add*(..))")
    public void beforeAddAccountAdvice(){

        System.out.println("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * Will match on any method that starts with "add", takes an aopdemo.Account object
     *      and any other number of arguments.
     */
    /*@Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
    public void beforeAddAccountAdvice(){

        System.out.println("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * This will match on any method that starts with "add" and takes an Account object from aopdemo
     * Account object must have fully-qualified path or else it doesn't know where to look
     */
    /*@Before("execution(* add*(com.luv2code.aopdemo.Account))")
    public void beforeAddAccountAdvice(){

        System.out.println("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * This will match ANY method that starts with "add", from any class, with any return type
     */
    /*@Before("execution(* add*())")
    public void beforeAddAccountAdvice(){

        System.out.println("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * This will match on any add, regardless of if its modifier
     */
    /*@Before("execution(void add*())")
    public void beforeAddAccountAdvice(){

        System.out.println("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * This will match on ANY method that starts with "add" from any class
     */
    /*@Before("execution(public void add*())")
    public void beforeAddAccountAdvice(){

        System.out.println("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * This will match on ANY add account with modifier "public" and return type "void"
     * regardless of class
     */
    /*@Before("execution(public void addAccount())")
    public void beforeAddAccountAdvice(){

        System.out.println("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * This will match ONLY the addAccount() method from AccountDAO.
     * Will NOT match on MembershipDAO's addAccount()
     */
    /*@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
    public void beforeAddAccountAdvice(){

        System.out.println("\n=====>>> Executing @Before advice on addAccount()");

    }*/

    /**
     * This will NOT match anything because there isn't an updateAccount() until we make one
     */
    /*@Before("execution(public void updateAccount())")
    public void beforeAddAccountAdvice(){

        System.out.println("\n=====>>> Executing @Before advice on method");

    }*/
}
