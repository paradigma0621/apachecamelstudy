package com.paradigma0621.apachecamelstudyA.routes.c;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestApiConsumerRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        restConfiguration().host("localhost").port(8000);   // Microservice B

        from("timer:rest-api-consumer?period=5000")      // Makes a GET HTTP Request every 5 seconds
            .setHeader("from", () -> "EUR")
            .setHeader("to", () -> "INR")
            .log("${body}")
            .to("rest:get:/currency-exchange/from/{from}/to/{to}") // localhost:8000/currency-exchange/EUR/aaa/to/INR
            .log("${body}");

    }

}