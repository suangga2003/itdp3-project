package com.example.miniprojectjava.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponseDTO {
    @JsonProperty("transaction_id")
    private Integer transactionId;

    @JsonProperty("account_from")
    private Integer accountFromId;

    @JsonProperty("account_to")
    private Integer accountToId;

    @JsonProperty("currency")
    private String currencyName;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("status")
    private String status;

    @JsonProperty("created_at")
    private Date createdAt;
}
