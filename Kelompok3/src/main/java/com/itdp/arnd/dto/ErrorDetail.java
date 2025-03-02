package com.itdp.arnd.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorDetail {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetail(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
