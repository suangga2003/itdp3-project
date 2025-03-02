package com.bjb.bankmanagement.forextransaction.dto;

import com.bjb.bankmanagement.forextransaction.entity.TransactionHistories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionHistoryDto implements Serializable {
    private List<TransactionHistories> transactionHistories;

    private String rc;
    private String rcDescription;
}

