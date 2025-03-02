package com.forex.exchange.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositWithdrawDto {
    private Long userId;
    private String currency;
    private BigDecimal amount;
}
