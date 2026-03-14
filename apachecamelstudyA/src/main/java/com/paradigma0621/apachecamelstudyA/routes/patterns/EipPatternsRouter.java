package com.paradigma0621.apachecamelstudyA.routes.patterns;

import com.paradigma0621.apachecamelstudyA.model.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EipPatternsRouter extends RouteBuilder {

    private final DynamicRouterBean dynamicRouterBean;

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


        //from("file:files/csv")
        //    .unmarshal().csv()          // Converts incoming serialized data (CSV, JSON, XML, etc.) into Java objects.
        //                                // It deserializes the message body so Camel routes can process structured data.
        //                                // Example: CSV text → List<List<String>> or JSON → Java DTO.
        //
        //    .split(body())                                  // break a message into multiple smaller messages and
        //                                                    // process each one separately.
        //                                                    // split takes the list and creates one message per element.
        //    .to("activemq:split-queue");


        //from("file:files/aggregate-json")
        //        .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)  // Deserialize JSON files into Java
        //                                                                    // objects so Camel can process their fields
        //        .aggregate(simple("${body.id}"), new ArrayListAggregationStrategy())
        //                // Aggregate EIP: groups incoming messages based on the value of the "id" field in the JSON body
        //        .completionSize(2)  // Complete the aggregation when 2 messages with the same key are received,
        //                            // combining them into a single message (List)
        //        //.completionTimeout(HIGHEST) // Alternative completion condition: release aggregation
        //                                      // after a time limit
        //        .to("log:aggregate-json"); // Send the aggregated message to the log endpoint


        // Routing Slip EIP: allows the message to carry a list of endpoints that define
        // the processing path dynamically at runtime. Camel will route the message
        // sequentially through each endpoint specified in the routing slip.
        // Unlike static routes, the message path is determined at runtime instead of being fixed in the code.
        //String routingSlip = "direct:endpoint1,direct:endpoint2,direct:endpoint3";
        //
        //from("timer:routingSlip?period=5000")
        //.transform().constant("My Message is Hardcoded")
        //.routingSlip(simple(routingSlip));


        // Dynamic Router EIP
        // Instead of defining a fixed route, the next endpoint is decided at runtime
        // by a bean that returns the next destination(s) for the message.
        from("timer:dynamicRouting?period=5000")
                .transform().constant("My message hardcoded")
                .dynamicRouter(method(dynamicRouterBean));      // Delegates routing decision to the DynamicRouterBean


        from("direct:endpoint1")
                .to("log:directendpoint1");

        from("direct:endpoint2")
                .to("log:directendpoint2");

        from("direct:endpoint3")
                .to("log:directendpoint3");

    }
}
