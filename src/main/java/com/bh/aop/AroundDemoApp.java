package com.bh.aop;

import com.bh.aop.dao.AccountDAO;
import com.bh.aop.demo.DemoConfig;
import com.bh.aop.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;


public class AroundDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);

		TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

		System.out.println("Calling getFortune");

		String data = trafficFortuneService.getFortune();

		System.out.println(data);
		
		context.close();
	}
}