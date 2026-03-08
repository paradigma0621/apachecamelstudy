package com.paradigma0621.apachecamelstudyA.routes.a.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleLoggingProcessingComponent {
    public void process(Exchange exchange) throws Exception {
        log.info("SimpleLoggingProcessor {}", exchange.getMessage().getBody());
    }
}
