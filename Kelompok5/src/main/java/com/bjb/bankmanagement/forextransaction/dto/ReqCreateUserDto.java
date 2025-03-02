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
public class ReqCreateUserDto implements Serializable {
    private static final long serialVersionUID = -7120267894251588769L;

    private String fullname;
    private String gender;
    private String placeOfBirth;
    private String dateOfBirth;
    private String province;
    private String address;
    private String city;
    private String district;
    private String subdistrict;
    private String postalCode;
    private String identityType;
    private String identityNumber;
    private String phoneNumber;
    private String accountNumber;
    private String currencyCode;
    private String email;
    private String password;
    private Double balance;
    private String pin;

}
