package com.bjb.bankmanagement.forextransaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqTransferDto implements Serializable {
    private static final long serialVersionUID = -9017515362184597881L;

    private String fromAccountNumber;
    private String toAccountNumber;
    private Double amount;
    private String pin;

}
