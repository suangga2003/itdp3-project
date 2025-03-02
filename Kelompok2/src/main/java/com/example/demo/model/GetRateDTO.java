package com.example.demo.model;

import lombok.Data;

@Data
public class GetRateDTO {
    private Integer rateId;
    private Double exchangeRate;
    private Double resultAmount;
}
