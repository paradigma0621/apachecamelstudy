package com.paradigma0621.apachecamelstudyB.model;

import java.math.BigDecimal;

public record CurrencyExchange(Long id, String from, String to, BigDecimal conversionMultiple) { }
