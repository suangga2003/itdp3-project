package com.example.bintang.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_seq")
    @SequenceGenerator(name = "transactions_seq", sequenceName = "transactions_id_seq", allocationSize = 1)
    @Schema(hidden = true)
    private Integer id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "id")
    @Schema(hidden = true)
    @JsonIgnore
    private Customer customer;

    @Column(name = "from_account")
    private String fromAccount; // Rekening sumber

    @Column(name = "to_account")
    private String toAccount; // Rekening tujuan

    @Column(name = "from_currency", nullable = false)
    private String fromCurrency;

    @Column(name = "to_currency", nullable = false)
    private String toCurrency;

    @Column(name = "amount", nullable = false)
    private Float amount;

    @ManyToOne
    @JoinColumn(name = "exchange_rate_id", nullable = false, referencedColumnName = "id")
    @Schema(hidden = true)
    private ForeignExchangeMarket exchangeRate;

    @Column(name = "exchange_rate_at_transaction", nullable = false)
    @Schema(hidden = true)
    private Float exchangeRateAtTransaction;

    @Column(name = "converted_amount", nullable = false)
    @Schema(hidden = true)
    private Float convertedAmount;

    @Column(name = "from_account_after_balance", nullable = false)
    @Schema(hidden = true)
    private Float fromAccountBalanceAfter;

    @Column(name = "to_account_after_balance", nullable = false)
    @Schema(hidden = true)
    private Float toAccountBalanceAfter;

    @Column(name = "status", nullable = false)
    @Schema(hidden = true)
    private String status; // Status transaksi (PENDING, SUCCESS, FAILED)

    @Transient
    @JsonIgnore
    private String rc;

    @Transient
    @JsonIgnore
    private String rcDesc;

    @PrePersist
    protected void onCreate() {
        this.dateTime = LocalDateTime.now(); // Automatically set timestamp before persisting
    }
}
