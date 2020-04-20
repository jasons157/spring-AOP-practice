package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * This app is for testing out @AfterReturning
 */
public class AfterReturningDemoApp {
    public static void main(String[] args) {

        //read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        //get account bean from spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        //call method to find the accounts
        boolean tripWire = false;//This makes sure that the simulated exception in AccountDAO is not triggered (triggers in AfterReturningDemoApp)
        List<Account> accounts = accountDAO.findAccounts(tripWire);

        //display accounts
        System.out.println("Main program: AfterReturningDemoApp");
        System.out.println("-----------\n");
        for (Account tempAccount: accounts){
            System.out.println(tempAccount);
        }

        //close the context
        context.close();

        System.out.println("SDF");
    }
}
