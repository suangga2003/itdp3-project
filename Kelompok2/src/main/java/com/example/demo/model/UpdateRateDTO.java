package com.example.demo.model;

import lombok.Data;

@Data
public class UpdateRateDTO {
    private double amount;
    private String mataUangAsal;
    private String mataUangTujuan;
}
