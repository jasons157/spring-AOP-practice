package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * We use PSVM here to create objects and call their methods.
 * By calling their methods, we can see that AOP logging is working.
 */
public class MainDemoApp {
    public static void main(String[] args) {

        //read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        //get account bean from spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        //get membership bean from spring container
        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        //call account business method
        Account account = new Account();
        account.setName("Jason");
        account.setLevel("Super Platinum");
        
        accountDAO.addAccount(account, true);
        accountDAO.doWork();

        //call accountDao getter/setters
        accountDAO.setName("Hulk Hogan");
        accountDAO.setServiceCode("Gold");

        String name = accountDAO.getName();
        String code = accountDAO.getServiceCode();
        System.out.println("ACCOUNT: " + name + " " + code);

        //call membership business method
        membershipDAO.addSillyRandom();
        membershipDAO.goToSleep();

        //close the context
        context.close();
    }
}
