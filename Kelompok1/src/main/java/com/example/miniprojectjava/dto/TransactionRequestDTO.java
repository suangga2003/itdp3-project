package com.example.miniprojectjava.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequestDTO {
    private Integer accountFromId;
    private Integer accountToId;
    private Integer currencyId;
    private BigDecimal amount;
}
