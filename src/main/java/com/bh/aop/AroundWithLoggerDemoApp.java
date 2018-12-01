package com.bh.aop;

import com.bh.aop.demo.DemoConfig;
import com.bh.aop.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundWithLoggerDemoApp {

	private static Logger myLogger = Logger.getLogger(AroundWithLoggerDemoApp.class.getName());

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);

		TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

		myLogger.info("Calling getFortune");

		String data = trafficFortuneService.getFortune(false);

		myLogger.info(data);
		
		context.close();
	}
}