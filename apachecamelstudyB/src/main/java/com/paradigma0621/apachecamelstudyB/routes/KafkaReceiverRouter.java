package com.paradigma0621.apachecamelstudyB.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component // temporarily disabled to keep console logs clean for other learning routes
public class KafkaReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("kafka:myKafkaTopic")                // Kafka consumes myKafkaTopic topic
        .to("log:received-message-from-kafka");

    }

}