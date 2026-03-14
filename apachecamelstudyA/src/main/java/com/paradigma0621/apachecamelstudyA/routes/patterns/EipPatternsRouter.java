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

        //from("timer:multicast?period=5000")  // trigger messages every 5 seconds
        //        .multicast()                                     // send the message to multiple endpoints sequentially
        //        .to("log:something1 sequencial",            // first logging endpoint
        //            "log:something2 sequencial",             // second logging endpoint
        //            "log:something3 sequencial");            // third logging endpoint
        //
        //from("timer:multicast?period=5000")  // trigger messages every 5 seconds
        //        .multicast().parallelProcessing()              // send the message to multiple endpoints in parallel
        //        .to("log:something1 parallel",            // first logging endpoint
        //            "log:something2 parallel",             // second logging endpoint
        //            "log:something3 parallel");            // third logging endpoint


		from("file:files/csv")
            .unmarshal().csv()          // Converts incoming serialized data (CSV, JSON, XML, etc.) into Java objects.
                                        // It deserializes the message body so Camel routes can process structured data.
                                        // Example: CSV text → List<List<String>> or JSON → Java DTO.

            .split(body())                                  // break a message into multiple smaller messages and
                                                            // process each one separately.
                                                            // split takes the list and creates one message per element.
            .to("activemq:split-queue");
    }
}
