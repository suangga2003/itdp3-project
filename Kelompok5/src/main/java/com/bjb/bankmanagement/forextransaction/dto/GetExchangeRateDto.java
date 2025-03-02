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
public class GetExchangeRateDto implements Serializable {
    private static final long serialVersionUID = 7320128280278698052L;

    private ExchangeRateCustom exchangeRates;
    private Double resultAmount;

    private String rc;
    private String rcDescription;
}
