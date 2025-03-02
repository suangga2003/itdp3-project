package com.bjb.bankmanagement.forextransaction.dto;

import lombok.Data;

@Data
public class UpdateExchangeRateDto {
    private String fromCurrencyCode;
    private String toCurrencyCode;
    private Double exchangeRate;
}
