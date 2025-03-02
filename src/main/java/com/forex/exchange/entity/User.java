package com.forex.exchange.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String address;

    private String gender;

    @Column(nullable = false)
    private BigDecimal idrBalance = BigDecimal.valueOf(500000);

    @Column(nullable = false)
    private BigDecimal usdBalance = BigDecimal.valueOf(100);

    @Column(nullable = false)
    private BigDecimal jpyBalance = BigDecimal.valueOf(10000);
}