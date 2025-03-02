package com.itdp.arnd.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itdp.arnd.dto.ReqCreateTransaction;
import com.itdp.arnd.entity.BankBalances;
import com.itdp.arnd.entity.Currencies;
import com.itdp.arnd.entity.Transactions;
import com.itdp.arnd.exception.InvalidExchangeCurrency;
import com.itdp.arnd.exception.InvalidInputValue;
import com.itdp.arnd.exception.NotEnoughBalance;
import com.itdp.arnd.repository.BalanceRepository;
import com.itdp.arnd.repository.CurrencyRepository;
import com.itdp.arnd.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private BalanceRepository balanceRepository;


    @Transactional
    public Transactions createTransaction(ReqCreateTransaction request) {
        Transactions transaction = new Transactions();
        Currencies buyCurrency = currencyRepository.findById(request.getBuyCurrencyId()).orElse(null);
        Currencies sellCurrency = currencyRepository.findById(request.getSellCurrencyId()).orElse(null);
        BankBalances bankBalanceSell = balanceRepository.findByBankUserIdAndCurrencyId(request.getBankUserId(), request.getSellCurrencyId());
        BankBalances bankBalanceBuy = balanceRepository.findByBankUserIdAndCurrencyId(request.getBankUserId(), request.getBuyCurrencyId());
        Double result_value = 0.0;
        if (request.getStartValue() <= 0) {
            throw new InvalidInputValue();
        }
        if (bankBalanceSell.getBalance() < request.getStartValue()) {
            throw new NotEnoughBalance();
        }
        if (sellCurrency.getIsPrimary() == true && buyCurrency.getIsPrimary() == false) {
            transaction.setCurrencyRate(buyCurrency.getCurrencyRate());
            result_value = request.getStartValue() / buyCurrency.getCurrencyRate();
        } else if (sellCurrency.getIsPrimary() == false && buyCurrency.getIsPrimary() == true) {
            transaction.setCurrencyRate(sellCurrency.getCurrencyRate());
            result_value = request.getStartValue() * sellCurrency.getCurrencyRate();
        } else {
            throw new InvalidExchangeCurrency();
        }
        bankBalanceSell.setBalance(bankBalanceSell.getBalance() - request.getStartValue());
        bankBalanceBuy.setBalance(bankBalanceBuy.getBalance() + result_value);
        transaction.setBankUserId(request.getBankUserId());
        transaction.setSellCurrencyId(request.getSellCurrencyId());
        transaction.setBuyCurrencyId(request.getBuyCurrencyId());
        transaction.setCurrencyRate(buyCurrency.getCurrencyRate());
        transaction.setStartValue(request.getStartValue());
        transaction.setResultValue(result_value);
        transaction.setCreatedAt(Instant.now());
        transaction.setUpdatedAt(Instant.now());
        transaction = transactionRepository.save(transaction);
        return transaction;
    }
}
