package com.paradigma0621.apachecamelstudyA.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFirstTimerRouter extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
		from("timer:first-timer")
		.to("log:first-timer");  // Prints null message in console log
	}

}