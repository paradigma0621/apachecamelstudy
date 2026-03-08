package com.paradigma0621.apachecamelstudyA.routes.a;

import com.paradigma0621.apachecamelstudyA.routes.a.bean.GetCurrentTimeBean;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MyFirstTimerRouter extends RouteBuilder{

	private final GetCurrentTimeBean getCurrentTimeBean;

	@Override
	public void configure() throws Exception {
		from("timer:first-timer")
		// .transform().constant("My constant message") // Prints constant message in console log
		//.transform().constant("Time now is: " + LocalDateTime.now()) // Prints the same time message every time
		.bean(getCurrentTimeBean) // now correctly updates and displays the current time
									// the method name is not specified because the class has only one method
									// if the class has multiple methods, specify the method name:
									// .bean(getCurrentTimeBean, "getCurrentTimeOrOtherMethodName")
		.to("log:first-timer");
	}

}