package com.example.miniprojectjava.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRateRequestDTO {
    private Integer currencyFromId;
    private Integer currencyToId;
    private BigDecimal rate;
}
