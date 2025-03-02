package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "rate")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column(name = "mata_uang_asal")
    private String mataUangAsal;

    @Column(name = "mata_uang_tujuan")
    private String mataUangTujuan;

    @Column(name = "kurs")
    private Double kurs;

    @Transient
    private String rc;

    @Transient
    private String rcDesc;
}
