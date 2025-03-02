package com.bjb.bankmanagement.forextransaction.dto;

import com.bjb.bankmanagement.forextransaction.entity.Currencies;
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
public class GetCurrienciesDto implements Serializable {
    private static final long serialVersionUID = 1614700481294176089L;

    private List<Currencies> currencies;

    private String rc;
    private String rcDescription;
}
