package com.example.bintang.dto;

import lombok.Data;

@Data
public class CustomerAccountRequest {
    private Integer customerId;
    private String type;
    private String account;
    private Float balance;
}

