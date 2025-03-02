
package com.forex.exchange.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRateDto {
    private Long id;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal rate;
}
