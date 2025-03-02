package com.forex.exchange.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private String name;
    private String email;
    private String address;
    private String gender;
}
