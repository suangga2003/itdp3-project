package com.bjb.bankmanagement.forextransaction.dto;

import lombok.Data;

@Data
public class UpdateUserRequestDto {
    private String fullname;
    private String gender;
    private String placeOfBirth;
    private String dateOfBirth;
    private String province;
    private String city;
    private String district;
    private String subdistrict;
    private String postalCode;
    private String identityType;
    private String identityNumber;
    private String phoneNumber;
    private String email;
    private String password;
    private String pin;


    private String rc;
    private String message;

    public UpdateUserRequestDto() {}

    public UpdateUserRequestDto(String rc, String message) {
        this.rc = rc;
        this.message = message;
    }
}
