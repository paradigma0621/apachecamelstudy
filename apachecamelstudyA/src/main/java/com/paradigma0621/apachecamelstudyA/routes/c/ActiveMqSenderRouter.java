package com.paradigma0621.apachecamelstudyA.routes.c;


import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //        from("timer:active-mq-timer?period=10000")           // Simple message sending to queue
        //        .transform().constant("My message for Active MQ")
        //        .log("${body}")
        //        .to("activemq:my-activemq-queue");

		from("file:files/json") // Sending JSON files to the queue. The JSON will be deserialized in microservice B
		.log("${body}")
		.to("activemq:my-activemq-queue");
    }
}