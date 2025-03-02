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
public class GetCreateUserDto implements Serializable {
    private static final long serialVersionUID = -7735872444437203203L;

    private ReqCreateUserDto user;

    private String rc;
    private String rcDescription;
}
