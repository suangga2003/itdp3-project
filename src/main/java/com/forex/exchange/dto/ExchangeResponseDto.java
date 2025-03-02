package com.forex.exchange.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ExchangeResponseDto {
    private Long userId;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal amountExchanged;
    private BigDecimal convertedAmount;
    private BigDecimal rate;
    private Map<String, BigDecimal> newBalance;
}
