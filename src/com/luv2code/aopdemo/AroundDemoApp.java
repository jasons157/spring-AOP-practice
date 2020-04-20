package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * This app is for testing out @Around
 */
public class AroundDemoApp {
    public static void main(String[] args) {

        //read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        //get account bean from spring container
        TrafficFortuneService trafficFortuneService =
                context.getBean("trafficFortuneService", TrafficFortuneService.class);

        System.out.println("\nMain program: AroundDemoApp");

        System.out.println("Calling getFortune()...");
        String data = trafficFortuneService.getFortune();

        System.out.println("\nMy fortune is: " + data);

        System.out.println("Finished");

        //close the context
        context.close();
    }
}
