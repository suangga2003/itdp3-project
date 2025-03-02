package com.bjb.bankmanagement.forextransaction.dto;

import com.bjb.bankmanagement.forextransaction.entity.UserAccounts;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class AccountBalanceDto {
    private UserAccounts userAccount;

    private String rc;
    private String rcDescription;
}
