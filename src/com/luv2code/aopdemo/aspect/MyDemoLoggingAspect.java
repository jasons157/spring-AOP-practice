package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

/**
 * This is where we add all of our related advice for logging
 */
@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Will happen before and after the pointcut expression.
     * This one will calculate the total runtime for getFortune()
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.luv2code.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{//Around uses ProceedingJoinPoint instead of JoinPoint

        //print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        logger.info("\n=====>>> Executing @Around on method: " + method);

        //get begin timestamp
        long start = System.currentTimeMillis();

        //execute the method
        Object result = proceedingJoinPoint.proceed();

        //get end timestamp
        long end = System.currentTimeMillis();

        //calculate duration and display
        logger.info("Total duration = " + (end-start)/1000 + " seconds");

        //Return result to calling program
        return result;
    }

    /**
     * Will output information after a method is called, regardless of success or failure
     * @param joinPoint
     */
    @After("execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint){

        //print method advising on
        String method = joinPoint.getSignature().toShortString();
        logger.info("\n=====>>> Executing @After (finally) on method: " + method);
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
        logger.info("\n=====>>> Executing @AfterReturning on method: " + method);

        //log exception
        logger.info("\n=====>>> exception is: " + exception);
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
        logger.info("\n=====>>> Executing @AfterReturning on method: " + method);

        //print out results of method call
        logger.info("\n=====>>> result is: " + result);

        //modify data before it gets returned to calling method

        /**
         * WARNING!!!
         * Modifying the returned data can be really confusing
         * for people who don't know the AOP is intercepting and tampering with
         * the results. Avoid this unless dev team knows what to expect.
         */

        //convert names toUpper
        convertAccountNamesToUpperCase(result);
        logger.info("\n=====>>> result is: " + result);


    }

    //utility method to update all the names to uppercase
    private void convertAccountNamesToUpperCase(List<Account> result) {
        for (Account tempAccount: result){
            tempAccount.setName(tempAccount.getName().toUpperCase());
        }
    }


    @Before("com.luv2code.aopdemo.aspect.AopExpressions.daoPackageExcludeGettersSetters()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint){
        logger.info("\n=====>>> Executing @Before advice on method");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        logger.info("Method: " + methodSignature);

        //display method args
        Object args[] = joinPoint.getArgs();
        for(Object tempArg:args){
            logger.info(tempArg.toString());

            if (tempArg instanceof Account){
                //downcast and print account specific stuff
                Account tempAccount = (Account) tempArg;

                logger.info("Account name: " + tempAccount.getName());
                logger.info("Account level: " + tempAccount.getLevel());

            }
        }
    }


    /**
     * This will match on any add() with any return type, any modifer, with any # of args (0..n)
     */
    /*@Before("execution(* add*(..))")
    public void beforeAddAccountAdvice(){

        logger.info("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * Will match on any method that starts with "add", takes an aopdemo.Account object
     *      and any other number of arguments.
     */
    /*@Before("execution(* add*(com.luv2code.aopdemo.Account, ..))")
    public void beforeAddAccountAdvice(){

        logger.info("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * This will match on any method that starts with "add" and takes an Account object from aopdemo
     * Account object must have fully-qualified path or else it doesn't know where to look
     */
    /*@Before("execution(* add*(com.luv2code.aopdemo.Account))")
    public void beforeAddAccountAdvice(){

        logger.info("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * This will match ANY method that starts with "add", from any class, with any return type
     */
    /*@Before("execution(* add*())")
    public void beforeAddAccountAdvice(){

        logger.info("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * This will match on any add, regardless of if its modifier
     */
    /*@Before("execution(void add*())")
    public void beforeAddAccountAdvice(){

        logger.info("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * This will match on ANY method that starts with "add" from any class
     */
    /*@Before("execution(public void add*())")
    public void beforeAddAccountAdvice(){

        logger.info("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * This will match on ANY add account with modifier "public" and return type "void"
     * regardless of class
     */
    /*@Before("execution(public void addAccount())")
    public void beforeAddAccountAdvice(){

        logger.info("\n=====>>> Executing @Before advice on method");

    }*/

    /**
     * This will match ONLY the addAccount() method from AccountDAO.
     * Will NOT match on MembershipDAO's addAccount()
     */
    /*@Before("execution(public void com.luv2code.aopdemo.dao.AccountDAO.addAccount())")
    public void beforeAddAccountAdvice(){

        logger.info("\n=====>>> Executing @Before advice on addAccount()");

    }*/

    /**
     * This will NOT match anything because there isn't an updateAccount() until we make one
     */
    /*@Before("execution(public void updateAccount())")
    public void beforeAddAccountAdvice(){

        logger.info("\n=====>>> Executing @Before advice on method");

    }*/
}
