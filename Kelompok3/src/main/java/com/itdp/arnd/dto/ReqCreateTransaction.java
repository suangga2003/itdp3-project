package com.itdp.arnd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ReqCreateTransaction {
    @JsonProperty("bank_user_id")
    private Integer bankUserId;
    @JsonProperty("sell_currency_id")
    private Integer sellCurrencyId;
    @JsonProperty("buy_currency_id")
    private Integer buyCurrencyId;
    @JsonProperty("start_value")
    private Double startValue;
}
