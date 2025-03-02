package com.bjb.bankmanagement.forextransaction.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transaction_histories")
public class TransactionHistories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_user_account_id", nullable = false)
    private Long fromUserAccountId;

    @Column(name = "dest_user_account_id", nullable = false)
    private Long destUserAccountId;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "from_trans_amount", nullable = false)
    private Double fromTransAmount;

    @Column(name = "dest_trans_amount", nullable = false)
    private Double destTransAmount;

    @Column(name = "from_currency", nullable = false)
    private String fromCurrency;

    @Column(name = "dest_currency", nullable = false)
    private String destCurrency;

    @Column(name = "exchange_rate", nullable = false)
    private Double exchangeRate;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
