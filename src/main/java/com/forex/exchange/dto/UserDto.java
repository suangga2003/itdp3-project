package com.forex.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String address;
    private String gender;
    private Map<String, BigDecimal> balance = new HashMap<>();
}










