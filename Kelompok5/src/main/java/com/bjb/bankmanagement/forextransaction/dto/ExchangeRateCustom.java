package com.bjb.bankmanagement.forextransaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateCustom implements Serializable {
    private static final long serialVersionUID = -5174923778593191297L;

    private String fromCurrencyCode;
    private String toCurrencyCode;
    private Double exchangeRate;
    private String rateDate;
}
