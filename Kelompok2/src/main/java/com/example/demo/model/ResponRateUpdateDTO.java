package com.example.demo.model;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ResponRateUpdateDTO {
    private String mataUangAsal;
    private String mataUangTujuan;
    private Double kurs;
}
