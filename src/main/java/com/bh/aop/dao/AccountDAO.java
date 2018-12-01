package com.bh.aop.dao;

import com.bh.aop.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    private String name;

    private String serviceCode;

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