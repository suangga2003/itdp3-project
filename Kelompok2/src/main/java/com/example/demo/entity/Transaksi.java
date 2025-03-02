package com.example.demo.entity;

import java.sql.Date;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "transaksi")
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaksiId")
    private Integer transaksiId;

    @Column(name = "jumlahIn")
    private Double jumlahIn;

    @Column(name = "jumlahOut")
    private Double jumlahOut;

    @Column(name = "waktuTransaksi")
    private LocalDateTime waktuTransaksi;

    @Column(name = "rateId")
    private Integer rateId;

    @Column(name = "akunId")
    private Integer akunId;

    @Column(name = "userId")
    private Integer userId;
}
