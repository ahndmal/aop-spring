package com.bh.aop;

import com.bh.aop.demo.DemoConfig;
import com.bh.aop.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class AroundHandleExceptionDemoApp {

	private static Logger myLogger = Logger.getLogger(AroundHandleExceptionDemoApp.class.getName());

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);

		TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

		myLogger.info("Calling getFortune");

		boolean tripWire = true;

		String data = trafficFortuneService.getFortune(tripWire);

		myLogger.info(data);
		
		context.close();
	}
}