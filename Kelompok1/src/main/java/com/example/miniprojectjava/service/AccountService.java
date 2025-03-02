package com.example.miniprojectjava.service;

import com.example.miniprojectjava.entity.Account;
import com.example.miniprojectjava.entity.Currency;
import com.example.miniprojectjava.entity.User;
import com.example.miniprojectjava.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public Account getAccountById(int id) {
        return accountRepository.findById(id).get();
    }

    public Account createAccount(Account request) {
        Account response = request;

        Account account = new Account();

        account.setUser(new User(request.getUser().getUserId()));
        account.setCurrency(new Currency(request.getCurrency().getCurrencyId()));
        account.setBalance(request.getBalance());

        accountRepository.save(account);
        return response;
    }

    public List<Account> getAccountByUserId(int userId) {
        return accountRepository.findAllByUser_UserId(userId);
    }

    public List<Map<String, Object>> getBalanceWithCurrencyByUserId(int userId) {
        return accountRepository.findAllByUser_UserId(userId).stream()
                .map(account -> Map.of(
                        "balance", account.getBalance(),
                        "currency", account.getCurrency()
                ))
                .collect(Collectors.toList());
    }
}
