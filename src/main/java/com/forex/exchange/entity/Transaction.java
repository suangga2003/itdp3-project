package com.forex.exchange.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String transactionType;

    private String fromCurrency;

    @Column(nullable = false)
    private String toCurrency;

    @Column(nullable = false)
    private BigDecimal amount;

    private BigDecimal convertedAmount;

    private BigDecimal rate;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    private BigDecimal newIdrBalance;
    private BigDecimal newUsdBalance;
    private BigDecimal newJpyBalance;
}