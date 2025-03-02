package com.itdp.arnd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Data
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "bank_user_id")
    @JsonProperty("bank_user_id")
    private Integer bankUserId;

    @Column(name = "sell_currency_id")
    @JsonProperty("sell_currency_id")
    private Integer sellCurrencyId;

    @Column(name = "buy_currency_id")
    @JsonProperty("buy_currency_id")
    private Integer buyCurrencyId;

    @Column(name = "currency_rate")
    @JsonProperty("currency_rate")
    private Double currencyRate;

    @Column(name = "start_value")
    @JsonProperty("start_value")
    private Double startValue;

    @Column(name = "result_value")
    @JsonProperty("result_value")
    private Double resultValue;

    @CreatedDate
    @Column(name = "created_at")
    @JsonProperty("created_at")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @JsonProperty("updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    @JsonProperty("deleted_at")
    private Instant deletedAt;
}
