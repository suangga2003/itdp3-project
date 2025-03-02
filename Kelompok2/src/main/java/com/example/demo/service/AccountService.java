package com.example.demo.service;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.model.AkunDTO;
import com.example.demo.repository.AccountsRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {

    @Autowired
    UserRepository userRepository;

    // pake instance bukan static
    @Autowired
    AccountsRepository accountsRepository;

    public Account createAccount(Account request) {
        Account response = request;

        accountsRepository.save(response);

        return response;
    }

    public AkunDTO getAllAccount(Integer request){
        List<Account> accounts =  accountsRepository.findAllByUsersId(request);
        User user = userRepository.findById(request).get();
        AkunDTO response = new AkunDTO();
        response.setAccounts(accounts);
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());

        return response;

    }

    public Account getAccountByNorek(String norek){
        try {
            Account account = new Account();
            account = accountsRepository.findAllByNoRek(norek);

            account.setRc("0000");
            account.setRcDesc("Success");

            return account;

        } catch (Exception e){
            Account account = new Account();

            account.setRc("4444");
            account.setRcDesc("No Rekening tidak dikenal!");

            return account;
        }
    }
}
