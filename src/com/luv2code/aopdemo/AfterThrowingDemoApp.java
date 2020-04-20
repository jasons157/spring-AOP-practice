package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * This app is for testing out @AfterReturning
 */
public class AfterThrowingDemoApp {
    public static void main(String[] args) {

        //read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        //get account bean from spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        //call method to find the accounts
        List<Account> accounts = null;
        try{
            //add a boolean flag to simulate exception
            boolean tripWire = true;

            accounts = accountDAO.findAccounts(tripWire);
        } catch (Exception e) {
            System.out.println("Main program caught an exception: " + e);
        }

        //display accounts
        System.out.println("Main program: AfterThrowingDemoApp");
        System.out.println("-----------\n");
        System.out.println(accounts);

        //close the context
        context.close();
    }
}
