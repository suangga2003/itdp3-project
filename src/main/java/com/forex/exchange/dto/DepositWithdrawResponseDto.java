package com.forex.exchange.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class DepositWithdrawResponseDto {
    private Long userId;
    private String currency;
    private BigDecimal amount;
    private Map<String, BigDecimal> newBalance;
}
