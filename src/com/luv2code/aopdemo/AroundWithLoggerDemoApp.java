package com.luv2code.aopdemo;

import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

/**
 * This app is for testing out @Around with a logger
 */
public class AroundWithLoggerDemoApp {

    private static Logger logger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());//JDK's built-in logger

    public static void main(String[] args) {

        //read spring config java class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        //get account bean from spring container
        TrafficFortuneService trafficFortuneService =
                context.getBean("trafficFortuneService", TrafficFortuneService.class);

        logger.info("\nMain program: AroundDemoApp");

        logger.info("Calling getFortune()...");
        String data = trafficFortuneService.getFortune();

        logger.info("\nMy fortune is: " + data);

        logger.info("Finished");

        //close the context
        context.close();
    }
}
