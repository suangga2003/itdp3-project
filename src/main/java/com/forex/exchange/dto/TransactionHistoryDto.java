package com.forex.exchange.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionHistoryDto {
    private Long id;
    private Long userId;
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal amountExchanged;
    private BigDecimal convertedAmount;
    private BigDecimal rate;
    private LocalDateTime timestamp;
}
