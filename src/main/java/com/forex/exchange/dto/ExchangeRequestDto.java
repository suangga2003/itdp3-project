package com.forex.exchange.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRequestDto {
    private Long userId;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal amount;
}
