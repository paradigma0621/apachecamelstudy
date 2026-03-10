package com.paradigma0621.apachecamelstudyB.routes;

import com.paradigma0621.apachecamelstudyB.model.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class ActiveMqReceiverRouter extends RouteBuilder {

    private final MyCurrencyExchangeProcessor myCurrencyExchangeProcessor;
    private final MyCurrencyExchangeTransformer myCurrencyExchangeTransformer;

    @Override
    public void configure() throws Exception {
        //from("activemq:my-activemq-queue")             // Simple transfer of text message
        //.to("log:received-message-from-active-mq");

        //from("activemq:my-activemq-queue")             // JSON transfer from Microservice A
        //.unmarshal()
        //.json(JsonLibrary.Jackson, CurrencyExchange.class)
        //.bean(myCurrencyExchangeProcessor)
        //.bean(myCurrencyExchangeTransformer)
        //.to("log:received-message-from-active-mq");

        from("activemq:my-activemq-xml-queue")              // XML transfer from Microservice A
		.unmarshal()
		.jacksonXml(CurrencyExchange.class)
		.to("log:received-message-from-activemq-xml");

    }
}


@Slf4j
@Component
class MyCurrencyExchangeProcessor {

    public void processMessage(CurrencyExchange currencyExchange) {

        log.info("Do some processing wiht currencyExchange.getConversionMultiple() value which is {}",
                currencyExchange.getConversionMultiple());

    }
}

@Component
class MyCurrencyExchangeTransformer {

    public CurrencyExchange processMessage(CurrencyExchange currencyExchange) {

        currencyExchange.setConversionMultiple(currencyExchange.getConversionMultiple().multiply(BigDecimal.TEN));

        return currencyExchange;

    }
}