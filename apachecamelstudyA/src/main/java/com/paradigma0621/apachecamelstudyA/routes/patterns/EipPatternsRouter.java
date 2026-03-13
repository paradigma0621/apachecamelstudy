package com.paradigma0621.apachecamelstudyA.routes.patterns;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class EipPatternsRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // EIP (Enterprise Integration Patterns)
        // Pipeline: sequential processing of messages (default behavior)
        // Multicast: send the same message to multiple endpoints

        from("timer:multicast?period=5000")  // trigger messages every 5 seconds
                .multicast()                                     // send the message to multiple endpoints sequentially
                .to("log:something1 sequencial",            // first logging endpoint
                    "log:something2 sequencial",             // second logging endpoint
                    "log:something3 sequencial");            // third logging endpoint

        from("timer:multicast?period=5000")  // trigger messages every 5 seconds
                .multicast().parallelProcessing()              // send the message to multiple endpoints in parallel
                .to("log:something1 parallel",            // first logging endpoint
                    "log:something2 parallel",             // second logging endpoint
                    "log:something3 parallel");            // third logging endpoint

    }
}
