package com.paradigma0621.apachecamelstudyA.routes.a;

import com.paradigma0621.apachecamelstudyA.routes.a.bean.GetCurrentTimeBean;
import com.paradigma0621.apachecamelstudyA.routes.a.bean.SimpleLoggingProcessingComponent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
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
		.process(new SimpleLoggingProcessor()) // another way to process the message - does not modify the message body
		.log("${body}")
		.to("log:first-timer");
	}

}

@Slf4j
class SimpleLoggingProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		log.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
	}

}