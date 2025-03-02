package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetTransferDTO {
    private Double hasilPembelianValas;
    private Double jumlahTransfer;
    private LocalDateTime waktuTransaksi;
}
