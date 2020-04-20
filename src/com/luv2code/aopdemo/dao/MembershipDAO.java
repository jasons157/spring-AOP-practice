package com.luv2code.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    //This will match on pointcut of "public void add*()"
    /*public void addSillyRandom(){
        System.out.println(getClass() + ": DOING STUFF: ADDING A MEMBERSHIP ACCOUNT");
    }*/

    //This won't match with pointcut of "void add*()" because the return type is different (boolean)
    //It WILL match pointcut of "* add*()" because the return type can be anything
    public boolean addSillyRandom(){
        System.out.println(getClass() + ": DOING STUFF: ADDING A MEMBERSHIP ACCOUNT");

        return true;
    }

    public void goToSleep(){

        System.out.println(getClass() + ": goToSleep()");

    }

}
