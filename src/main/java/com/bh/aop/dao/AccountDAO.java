package com.bh.aop.dao;

import com.bh.aop.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {

    private String name;
    private String serviceCode;

    public List<Account> findAccounts() {

        List<Account> myAccounts = new ArrayList<Account>();

        Account account1 = new Account("John", "Silver");
        Account account2 = new Account("Vasyl", "Gold");

        myAccounts.add(account1);
        myAccounts.add(account2);

        return myAccounts;
    }

	public void addAccount(Account theAccount, boolean vipFlag) {

		System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
	}

	public boolean doWork() {

		System.out.println(getClass() + ": doWork()");
		return false;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + " : in getServiceCode");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}