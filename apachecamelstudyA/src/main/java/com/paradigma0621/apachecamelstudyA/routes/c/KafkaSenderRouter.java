package com.paradigma0621.apachecamelstudyA.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component // temporarily disabled to keep console logs clean for other learning routes
public class KafkaSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:files/json")             // Kafka sends JSONs to myKafkaTopic topic
        .log("${body}")
        .to("kafka:myKafkaTopic");

    }
}