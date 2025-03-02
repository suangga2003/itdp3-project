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
public class GetUpdateUserDto implements Serializable {
    private static final long serialVersionUID = -15645866941142747L;

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
    private String rcDescription;
}
