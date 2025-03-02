package com.bjb.bankmanagement.forextransaction.dto;

import com.bjb.bankmanagement.forextransaction.entity.UserAccounts;
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
public class GetListAccountBalanceDto implements Serializable {
    private static final long serialVersionUID = -3178201672509580999L;

    private List<UserAccounts> userAccounts;

    private String rc;
    private String rcDescription;
}
