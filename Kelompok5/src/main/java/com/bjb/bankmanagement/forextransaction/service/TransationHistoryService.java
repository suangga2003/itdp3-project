package com.bjb.bankmanagement.forextransaction.service;

import com.bjb.bankmanagement.forextransaction.constant.ResponseCode;
import com.bjb.bankmanagement.forextransaction.dto.GetExchangeRateDto;
import com.bjb.bankmanagement.forextransaction.dto.GetTransferDto;
import com.bjb.bankmanagement.forextransaction.dto.ReqExchangeRateDto;
import com.bjb.bankmanagement.forextransaction.dto.ReqTransferDto;
import com.bjb.bankmanagement.forextransaction.entity.TransactionHistories;
import com.bjb.bankmanagement.forextransaction.entity.UserAccounts;
import com.bjb.bankmanagement.forextransaction.entity.UserAuthentications;
import com.bjb.bankmanagement.forextransaction.repository.AccountRepository;
import com.bjb.bankmanagement.forextransaction.repository.TransactionRepository;
import com.bjb.bankmanagement.forextransaction.repository.UserAuthenticationRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class TransationHistoryService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ExchangeRateService exchangeRateService;

    @Autowired
    UserAuthenticationRepository userAuthenticationRepository;


    @Transactional
    public GetTransferDto executeTransfer(ReqTransferDto request) {
        String errMessage = "";
        GetExchangeRateDto exchangeRateAmount = new GetExchangeRateDto();
        GetTransferDto response = new GetTransferDto();
        UserAccounts fromUserAccount = new UserAccounts();
        UserAccounts toUserAccount = new UserAccounts();
        TransactionHistories execute = null;

        try {

            fromUserAccount =  accountRepository.findByAccountNumber(request.getFromAccountNumber());
            toUserAccount =  accountRepository.findByAccountNumber(request.getToAccountNumber());

            if (ObjectUtils.isEmpty(fromUserAccount) && ObjectUtils.isEmpty(toUserAccount)) {
                errMessage = "User account sender and beneficiary doesn't found";
                throw new Exception(errMessage);
            } else if (ObjectUtils.isEmpty(fromUserAccount)) {
                errMessage = "User account sender doesn't found";
                throw new Exception(errMessage);
            } else if (ObjectUtils.isEmpty(toUserAccount)) {
                errMessage = "User account beneficiary doesn't found";
                throw new Exception(errMessage);
            }

            UserAuthentications userAuth = userAuthenticationRepository.findByUserProfileId(fromUserAccount.getUserProfileId());

            if (ObjectUtils.isEmpty(request.getPin())) {
                errMessage = "PIN is empty";
                throw new Exception(errMessage);
            }

            if (!request.getPin().equals(userAuth.getPin())) {
                errMessage = "PIN is wrong";
                throw new Exception(errMessage);
            }

            if (fromUserAccount.getBalance() < request.getAmount()) {
                errMessage = "Your balance is not enough";
                throw new Exception(errMessage);
            }

            ReqExchangeRateDto reqExchangeRateAmount = ReqExchangeRateDto.builder()
                    .fromCurrencyCode(fromUserAccount.getCurrencyCode())
                    .toCurrencyCode(toUserAccount.getCurrencyCode())
                    .amount(request.getAmount())
                    .build();

            exchangeRateAmount = exchangeRateService.getExchangeRates(reqExchangeRateAmount);

            if (ResponseCode.SUCCESS.getCode().equals(exchangeRateAmount.getRc())) {
                execute = TransactionHistories.builder()
                        .fromTransAmount(request.getAmount())
                        .destTransAmount(exchangeRateAmount.getResultAmount())
                        .fromUserAccountId(fromUserAccount.getId())
                        .destUserAccountId(toUserAccount.getId())
                        .fromCurrency(fromUserAccount.getCurrencyCode())
                        .destCurrency(toUserAccount.getCurrencyCode())
                        .exchangeRate(exchangeRateAmount.getExchangeRates().getExchangeRate())
                        .transactionDate(LocalDateTime.now())
                        .createdAt(LocalDateTime.now())
                        .build();

                transactionRepository.save(execute);

                fromUserAccount.setBalance(fromUserAccount.getBalance() - request.getAmount());
                fromUserAccount.setUpdatedAt(LocalDateTime.now());

                toUserAccount.setBalance(toUserAccount.getBalance() + exchangeRateAmount.getResultAmount());
                toUserAccount.setUpdatedAt(LocalDateTime.now());

                List<UserAccounts> users = new ArrayList<>();
                users.add(fromUserAccount);
                users.add(toUserAccount);

                accountRepository.saveAll(users);

            } else {
                errMessage = "Error when getting exchange rate data";
                throw new Exception(errMessage);
            }

            response = GetTransferDto.builder()
                    .transactionHistories(execute)
                    .rc(ResponseCode.SUCCESS.getCode())
                    .rcDescription("Successfully")
                    .build();
        } catch (Exception e) {
            response = GetTransferDto.builder()
                    .transactionHistories(execute)
                    .rc(ResponseCode.GENERAL_ERROR.getCode())
                    .rcDescription(ObjectUtils.isEmpty(errMessage) ? "General Error" : errMessage)
                    .build();
            log.error("Error : {}" + e.getMessage(), e);
        }

        return response;
    }
}
