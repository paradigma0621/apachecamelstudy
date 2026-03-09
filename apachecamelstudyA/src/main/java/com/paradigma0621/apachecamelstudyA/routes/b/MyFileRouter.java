package com.paradigma0621.apachecamelstudyA.routes.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component // temporarily disabled to keep console logs clean for other learning routes
public class MyFileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file:files/input") // reads files from "/USER_PROJECT_DIRECTORY/files/input"
                                    // and moves them to "/USER_PROJECT_DIRECTORY/files/output"
        .log("${body}")
        .to("file:files/output");
    }

}