package com.paradigma0621.apachecamelstudyA.routes.a;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BestPracticesProcessingComponent extends RouteBuilder {

    @Override
    public void configure() throws Exception {
                    // The execution interval is configured in application.properties (timePeriod=3000).
           // from("timer:logInConsole?period={{timePeriod}}")
                    // Sends the message to the endpoint defined in application.properties.
                    // This allows changing the logging destination without modifying the code
            //.to("{{endpoint-for-logging}}");
    }
}
