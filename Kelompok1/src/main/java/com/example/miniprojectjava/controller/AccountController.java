package com.example.miniprojectjava.controller;

import com.example.miniprojectjava.dto.AccountRequestDTO;
import com.example.miniprojectjava.entity.Account;
import com.example.miniprojectjava.entity.Currency;
import com.example.miniprojectjava.entity.User;
import com.example.miniprojectjava.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccount() { return accountService.getAllAccount();}

    @GetMapping("/getAccount")
    public Account getAccountById(int id) { return accountService.getAccountById(id);}

    @PostMapping("/createAccount")
    public ResponseEntity<AccountRequestDTO> create (@RequestBody AccountRequestDTO request) {

        AccountRequestDTO response = request;
        Account account = new Account();
        account.setUser(new User(request.getUserId()));
        account.setCurrency(new Currency(request.getCurrencyId()));
        account.setBalance(request.getBalance());
        accountService.createAccount(account);
//        Account response = accountService.createAccount(account);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAccountByUserId")
    public List<Account> getAccountByUserId(int userId) { return accountService.getAccountByUserId(userId);}

    @GetMapping("/getBalanceByUserId")
    public List<Map<String, Object>> getBalanceWithCurrencyByUserId(@RequestParam int userId) {
        return accountService.getBalanceWithCurrencyByUserId(userId);
    }

}
