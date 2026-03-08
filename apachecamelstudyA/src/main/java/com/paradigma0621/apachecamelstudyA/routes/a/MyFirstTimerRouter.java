package com.paradigma0621.apachecamelstudyA.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyFirstTimerRouter extends RouteBuilder{
	
	@Override
	public void configure() throws Exception {
		from("timer:first-timer")
		// .transform().constant("My constant message") // Prints constant message in console log
		.transform().constant("Time now is: " + LocalDateTime.now()) // Prints the same time message every time
		.to("log:first-timer");
	}

}