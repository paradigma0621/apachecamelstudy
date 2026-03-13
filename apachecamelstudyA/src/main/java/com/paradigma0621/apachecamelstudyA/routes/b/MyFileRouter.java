package com.paradigma0621.apachecamelstudyA.routes.b;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //        from("file:files/input") // reads files from "/USER_PROJECT_DIRECTORY/files/input"
        //                                    // and moves them to "/USER_PROJECT_DIRECTORY/files/output"
        //        .log("${body}")
        //        .to("file:files/output");


        from("file:files/input")
            .routeId("Files-Input-Route")
            .transform().body(String.class) // necessary to do: "${body} contains "
            .choice() //Content Based Routing
                .when(simple("${file:ext} == 'xml'")) // simple() means to use 'simple-language'
                //.when(simple("${file:ext} ends with 'xml'")) the same as above
                                            // https://camel.apache.org/components/4.18.x/languages/simple-language.html
                                            // https://camel.apache.org/components/4.18.x/languages/file-language.html
                    .log("XML FILE")
                .when(simple("${body} contains 'USD'"))
                    .log("Not an XML FILE BUT contains USD")
                .otherwise()
                    .log("Not an XML FILE")
            .end()
            //.log("${messageHistory} ${headers.CamelFileAbsolutePath} ${file:name}")
            .to("direct:log-file-values")                   // send message to internal route
            .to("file:files/output");

        from("direct:log-file-values")                       // create an internal route endpoint
                .log("Using: ${routeId}")
                .log("${messageHistory} ${file:absolute.path}")
                .log("${file:name} ${file:name.ext} ${file:name.noext} ${file:onlyname}")
                .log("${file:onlyname.noext} ${file:parent} ${file:path} ${file:absolute}")
                .log("${file:size} ${file:modified}")
                .log("${routeId} ${camelId} ${body}");
    }
}