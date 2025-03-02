package com.bjb.bankmanagement.forextransaction.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private String rc;
    private String message;

    public ResponseDto(String rc, String message) {
        this.rc = rc;
        this.message = message;
    }
}
