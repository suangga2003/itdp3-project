package com.bjb.bankmanagement.forextransaction.service;

import com.bjb.bankmanagement.forextransaction.constant.ResponseCode;
import com.bjb.bankmanagement.forextransaction.dto.GetCreateUserDto;
import com.bjb.bankmanagement.forextransaction.dto.GetUpdateUserDto;
import com.bjb.bankmanagement.forextransaction.dto.ReqCreateUserDto;
import com.bjb.bankmanagement.forextransaction.dto.UpdateUserRequestDto;
import com.bjb.bankmanagement.forextransaction.entity.UserAccounts;
import com.bjb.bankmanagement.forextransaction.entity.UserAuthentications;
import com.bjb.bankmanagement.forextransaction.entity.UserProfiles;
import com.bjb.bankmanagement.forextransaction.repository.AccountRepository;
import com.bjb.bankmanagement.forextransaction.repository.UserProfileRepository;
import com.bjb.bankmanagement.forextransaction.repository.UserAuthenticationRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserAuthenticationRepository userAuthenticationRepository;

    @Autowired
    private AccountRepository accountRepository;



    @Transactional
    public GetCreateUserDto createUser(ReqCreateUserDto request) {
        String errMessage = "";
        GetCreateUserDto response = new GetCreateUserDto();

        try {
            Optional<UserAuthentications> isUserExist = userAuthenticationRepository.findByEmail(request.getEmail());

            if (isUserExist.isPresent()) {
                errMessage = "Email have registered, please use other email";
                throw  new Exception(errMessage);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            UserProfiles userProfile = UserProfiles.builder()
                    .fullname(request.getFullname())
                    .gender(request.getGender())
                    .placeOfBirth(request.getPlaceOfBirth())
                    .dateOfBirth(LocalDate.parse(request.getDateOfBirth(), formatter))
                    .address(request.getAddress())
                    .province(request.getProvince())
                    .city(request.getCity())
                    .district(request.getDistrict())
                    .subdistrict(request.getSubdistrict())
                    .postalCode(request.getPostalCode())
                    .identityNumber(request.getIdentityNumber())
                    .identityType(request.getIdentityType())
                    .phoneNumber(request.getPhoneNumber())
                    .createdAt(LocalDateTime.now())
                    .build();
            userProfile = userProfileRepository.saveAndFlush(userProfile);

            UserAccounts userAccount = UserAccounts.builder()
                    .userProfileId(userProfile.getId())
                    .accountNumber(request.getAccountNumber())
                    .currencyCode(request.getCurrencyCode())
                    .balance(request.getBalance())
                    .createdAt(LocalDateTime.now())
                    .build();
            accountRepository.saveAndFlush(userAccount);

            UserAuthentications userAuth = UserAuthentications.builder()
                    .userProfileId(userProfile.getId())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .pin(request.getPin())
                    .createdAt(LocalDateTime.now())
                    .build();
            userAuthenticationRepository.saveAndFlush(userAuth);


            response = GetCreateUserDto.builder()
                    .user(request)
                    .rc(ResponseCode.SUCCESS.getCode())
                    .rcDescription("User created successfully")
                    .build();

        }catch (Exception e) {
            response = GetCreateUserDto.builder()
                    .user(request)
                    .rc(ResponseCode.GENERAL_ERROR.getCode())
                    .rcDescription("General Error")
                    .build();
            log.error("Error : {}" + e.getMessage(), e);
        }

        return response;
    }

    @Transactional
    public GetUpdateUserDto updateUser(String email, UpdateUserRequestDto request) {
        Optional<UserAuthentications> authOptional = userAuthenticationRepository.findByEmail(email);
        GetUpdateUserDto response = new GetUpdateUserDto();


        response.setFullname(request.getFullname());
        response.setGender(request.getGender());
        response.setEmail(request.getEmail());
        response.setPassword(request.getPassword());
        response.setPhoneNumber(request.getPhoneNumber());
        response.setPlaceOfBirth(request.getPlaceOfBirth());
        response.setDateOfBirth(request.getDateOfBirth());
        response.setIdentityType(request.getIdentityType());
        response.setIdentityNumber(request.getIdentityNumber());
        response.setProvince(request.getProvince());
        response.setCity(request.getCity());
        response.setDistrict(request.getDistrict());
        response.setSubdistrict(request.getSubdistrict());
        response.setPostalCode(request.getPostalCode());
        response.setPin(request.getPin());

        try{
            if (authOptional.isPresent()) {
                UserAuthentications auth = authOptional.get();
                Optional<UserProfiles> user = userProfileRepository.findById(auth.getUserProfileId());

                if (user.isPresent()) {
                    if (request.getFullname() != null) user.get().setFullname(request.getFullname());
                    if (request.getGender() != null) user.get().setGender(request.getGender());
                    if (request.getPlaceOfBirth() != null) user.get().setPlaceOfBirth(request.getPlaceOfBirth());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    if (request.getDateOfBirth() != null) user.get().setDateOfBirth(LocalDate.parse(request.getDateOfBirth(), formatter));

                    if (request.getProvince() != null) user.get().setProvince(request.getProvince());
                    if (request.getCity() != null) user.get().setCity(request.getCity());
                    if (request.getDistrict() != null) user.get().setDistrict(request.getDistrict());
                    if (request.getSubdistrict() != null) user.get().setSubdistrict(request.getSubdistrict());
                    if (request.getPostalCode() != null) user.get().setPostalCode(request.getPostalCode());

                    if (request.getIdentityType() != null) user.get().setIdentityType(request.getIdentityType());

                    if (request.getIdentityNumber() != null) user.get().setIdentityNumber(request.getIdentityNumber());
                    if (request.getPhoneNumber() != null) user.get().setPhoneNumber(request.getPhoneNumber());

                    user.get().setUpdatedAt(LocalDateTime.now());
                    userProfileRepository.save(user.get());

                    if (request.getPassword() != null) auth.setPassword(request.getPassword());
                    if (request.getPin() != null) auth.setPin(request.getPin());

                    auth.setUpdatedAt(LocalDateTime.now());
                    userAuthenticationRepository.save(auth);

                    response.setRc(ResponseCode.SUCCESS.getCode());
                    response.setRcDescription("Successfully");

                    return response;
                } else {
                    response.setRc(ResponseCode.SUCCESS.getCode());
                    response.setRcDescription("Successfully");
                }
            } else {
                response.setRc(ResponseCode.SUCCESS.getCode());
                response.setRcDescription("User acputhenctication not found");
            }
        } catch (Exception e) {
            response.setRc(ResponseCode.GENERAL_ERROR.getCode());
            response.setRcDescription("General Error");
            log.error("Error : {}" + e.getMessage(), e);
        }

        return response;
    }
}
