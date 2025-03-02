package com.itdp.arnd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FormatBalance {
    @JsonProperty("currency_id")
    private Integer currencyId;
    private String name;
    private Double balance;
}
