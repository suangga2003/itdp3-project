package com.example.miniprojectjava.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "t_transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "account_from", nullable = false)
    private Account accountFrom;

    @ManyToOne
    @JoinColumn(name = "account_to", nullable = false)
    private Account accountTo;

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @Column(name = "amount", nullable = false, precision = 20, scale = 10)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "rate_id", nullable = true)
    private ExchangeRate rate;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = true)
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = true)
    private Status status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
