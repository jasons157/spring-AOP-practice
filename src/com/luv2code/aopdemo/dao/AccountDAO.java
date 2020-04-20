package com.luv2code.aopdemo.dao;

import com.luv2code.aopdemo.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {

    private String name;
    private String serviceCode;

    /**
     * Just a dummy for finding accounts, just makes up accounts and returns
     * @return Some made up accounts
     * @param tripWire
     */
    public List<Account> findAccounts(boolean tripWire){

        //simulate an exception for learning purposes
        if (tripWire){
            throw new RuntimeException("I am throwing an arbitrary exception! :)");
        }

        List<Account> accounts = new ArrayList<>();

        //create sample accounts
        Account temp1 = new Account("Jason", "Super Platinum");
        Account temp2 = new Account("Taylor", "Bronze");
        Account temp3 = new Account("Mary", "Silver");

        //add them to list
        accounts.add(temp1);
        accounts.add(temp2);
        accounts.add(temp3);

        return accounts;
    }

    public void addAccount(Account account, boolean vipFlag){

        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");

    }

    public boolean doWork(){

        System.out.println(getClass() + ": doWork()");

        return false;
    }

    public String getName() {
        System.out.println(getClass() + ": getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": setServiceCode()");
        this.serviceCode = serviceCode;
    }
}

