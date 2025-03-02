package com.itdp.arnd.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
@Table(name ="bank_balances")
public class BankBalances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "balance")
    private Double balance;

    @Column(name = "bank_user_id")
    @JsonProperty("bank_user_id")
    private Integer bankUserId;

    @Column(name = "currency_id")
    @JsonProperty("currency_id")
    private Integer currencyId;

    @Column(name = "created_at")
    @JsonProperty("created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    @JsonProperty("deleted_at")
    private Instant deletedAt;
}
