package com.paradigma0621.apachecamelstudyA.routes.a;

import com.paradigma0621.apachecamelstudyA.routes.a.bean.GetCurrentTimeBean;
import com.paradigma0621.apachecamelstudyA.routes.a.bean.SimpleLoggingProcessingComponent;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MyFirstTimerRouter extends RouteBuilder{

	private final GetCurrentTimeBean getCurrentTimeBean;
	private final SimpleLoggingProcessingComponent loggingComponent;

	@Override
	public void configure() throws Exception {
		from("timer:first-timer")
		.log("${body}")
		.transform().constant("My constant message") // Prints constant message in console log
		//.transform().constant("Time now is: " + LocalDateTime.now()) // Prints the same time message every time
		.log("${body}")
		.bean(getCurrentTimeBean) // now correctly updates and displays the current time
									// the method name is not specified because the class has only one method
									// if the class has multiple methods, specify the method name:
									// .bean(getCurrentTimeBean, "getCurrentTimeOrOtherMethodName")

		.log("${body}")
		.bean(loggingComponent) // logs the message without modifying the body
		.log("${body}")
		.to("log:first-timer");
	}

}