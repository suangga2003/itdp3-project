package com.bjb.bankmanagement.forextransaction.service;

import com.bjb.bankmanagement.forextransaction.constant.ResponseCode;
import com.bjb.bankmanagement.forextransaction.dto.*;
import com.bjb.bankmanagement.forextransaction.entity.Currencies;
import com.bjb.bankmanagement.forextransaction.entity.UserAccounts;
import com.bjb.bankmanagement.forextransaction.entity.TransactionHistories;
import com.bjb.bankmanagement.forextransaction.repository.CurrencyRepository;
import com.bjb.bankmanagement.forextransaction.repository.UserAccountRepository;
import com.bjb.bankmanagement.forextransaction.repository.AccountRepository;
import com.bjb.bankmanagement.forextransaction.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserAccountRepository userAccountRepository;


    public GetCurrienciesDto getAllCurrencies(String code) {
        List<Currencies> data = new ArrayList<>();
        GetCurrienciesDto response = new GetCurrienciesDto();

        try {
            if (ObjectUtils.isEmpty(code)) {
                data = currencyRepository.findAll();
            } else {
                data = currencyRepository.findAllByCode(code);
            }

            response = GetCurrienciesDto.builder()
                    .currencies(data)
                    .rc(ResponseCode.SUCCESS.getCode())
                    .rcDescription(ObjectUtils.isEmpty(data) ? "Data not found" : "Successfully")
                    .build();
        } catch (Exception e) {
            response = GetCurrienciesDto.builder()
                    .currencies(data)
                    .rc(ResponseCode.GENERAL_ERROR.getCode())
                    .rcDescription("General Error")
                    .build();
            log.error("Error : {}" + e.getMessage(), e);
        }

        return response;
    }

    public GetListAccountBalanceDto getAccountBalance(Long userProfileId) {
        List<UserAccounts> accounts = new ArrayList<>();
        GetListAccountBalanceDto response;

        try {
            accounts = accountRepository.findByUserProfileId(userProfileId);

            response = GetListAccountBalanceDto.builder()
                    .userAccounts(accounts)
                    .rc(ResponseCode.SUCCESS.getCode())
                    .rcDescription(ObjectUtils.isEmpty(accounts) ? "Data not found" : "Successfully")
                    .build();
        } catch (Exception e) {
            response = GetListAccountBalanceDto.builder()
                    .userAccounts(accounts)
                    .rc(ResponseCode.GENERAL_ERROR.getCode())
                    .rcDescription("General Error")
                    .build();
            log.error("Error : {}" + e.getMessage(), e);
        }

        return response;
    }


    public AccountBalanceDto getAccountBalance(Long userProfileId, String accountNumber) {
        UserAccounts account = new UserAccounts();
        AccountBalanceDto response;

        try {
            account = accountRepository.findByUserProfileIdAndAccountNumber(userProfileId, accountNumber);

            response = AccountBalanceDto.builder()
                    .userAccount(account)
                    .rc(ResponseCode.SUCCESS.getCode())
                    .rcDescription(ObjectUtils.isEmpty(account) ? "Account not found" : "Successfully")
                    .build();
        } catch (Exception e) {
            response = AccountBalanceDto.builder()
                    .userAccount(account)
                    .rc(ResponseCode.GENERAL_ERROR.getCode())
                    .rcDescription("General Error")
                    .build();
            log.error("Error : {}" + e.getMessage(), e);
        }

        return response;
    }

    public TransactionHistoryDto getTransactionHistory(String accountNumber) {
        String errMessage = "";
        List<TransactionHistories> transactions = new ArrayList<>();
        TransactionHistoryDto response = new TransactionHistoryDto();

        try {
            UserAccounts userAccount = accountRepository.findByAccountNumber(accountNumber);

            if (ObjectUtils.isEmpty(userAccount)) {
                errMessage = "User account doesn't found";
                throw new Exception(errMessage);
            }

            transactions = transactionRepository.findByFromUserAccountId(userAccount.getId());

            response = TransactionHistoryDto.builder()
                    .transactionHistories(transactions)
                    .rc(ResponseCode.SUCCESS.getCode())
                    .rcDescription(ObjectUtils.isEmpty(transactions) ? "History transaction not found" : "Successfully")
                    .build();
        } catch (Exception e) {
            response = TransactionHistoryDto.builder()
                    .transactionHistories(transactions)
                    .rc(ResponseCode.GENERAL_ERROR.getCode())
                    .rcDescription(errMessage.isEmpty() ? "General Error" : errMessage)
                    .build();
            log.error("Error : {}" + e.getMessage(), e);
        }

        return response;
    }

    @Transactional
    public UpdateCurrencyDto updateCurrency(UpdateCurrencyDto request) {
        Optional<Currencies> existingCurrency = currencyRepository.findById(request.getId());

        // Jika ID mata uang tidak ditemukan
        if (existingCurrency.isEmpty()) {
            return UpdateCurrencyDto.builder()
                    .id(request.getId())
                    .code(request.getCode())
                    .description(request.getDescription())
                    .rc(ResponseCode.GENERAL_ERROR.getCode())
                    .rcDescription("Mata uang dengan ID " + request.getId() + " tidak ditemukan.")
                    .build();
        }

        Currencies currency = existingCurrency.get();

        // Cek apakah kode mata uang sudah digunakan di user_accounts
        boolean isCodeUsed = userAccountRepository.existsByCurrencyCode(currency.getCode());

        if (isCodeUsed && !currency.getCode().equals(request.getCode())) {
            return UpdateCurrencyDto.builder()
                    .id(Long.valueOf(currency.getId()))
                    .code(currency.getCode())
                    .description(currency.getDescription())
                    .rc(ResponseCode.GENERAL_ERROR.getCode())
                    .rcDescription("Mata uang dengan kode " + currency.getCode() + " sudah digunakan dan tidak dapat diubah.")
                    .build();
        }

        // Lakukan update jika kode belum digunakan
        currency.setCode(request.getCode());
        currency.setDescription(request.getDescription());
        currency.setUpdatedAt(LocalDateTime.now());
        currencyRepository.save(currency);

        return UpdateCurrencyDto.builder()
                .id(Long.valueOf(currency.getId()))
                .code(currency.getCode())
                .description(currency.getDescription())
                .rc(ResponseCode.SUCCESS.getCode())
                .rcDescription("Mata uang berhasil diperbarui.")
                .build();
    }
}

