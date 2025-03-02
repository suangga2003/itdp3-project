package com.example.miniprojectjava.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequestDTO {
    private Integer userId;
    private Integer currencyId;
    private BigDecimal balance;
}
