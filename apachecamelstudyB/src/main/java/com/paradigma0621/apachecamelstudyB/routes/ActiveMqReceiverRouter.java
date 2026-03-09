package com.paradigma0621.apachecamelstudyB.routes;

import com.paradigma0621.apachecamelstudyB.model.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ActiveMqReceiverRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //from("activemq:my-activemq-queue")             // Simple transfer of text message
        //.to("log:received-message-from-active-mq");

        from("activemq:my-activemq-queue")
        .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
        .to("log:received-message-from-active-mq");
    }
}
