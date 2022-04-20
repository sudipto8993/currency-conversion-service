package com.boot.microservices.controller;

import com.boot.microservices.bean.CurrencyConversion;
import com.boot.microservices.service.CurrencyConversionProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currencyconversion")
public class CurrencyConversionController {

    @Autowired
    private CurrencyConversionProxy currencyConversionProxy;

    @GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion getCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {

        CurrencyConversion currencyConversionResponse = currencyConversionProxy.retrieveExchangeValue(from, to);
        return new CurrencyConversion(currencyConversionResponse.getId(), from, to,
                currencyConversionResponse.getConversionMultiple(), quantity,
                quantity.multiply(currencyConversionResponse.getConversionMultiple()));
    }
}
