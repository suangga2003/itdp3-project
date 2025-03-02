package com.itdp.arnd.controller;

import com.itdp.arnd.dto.GetCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itdp.arnd.dto.ApiResponse;
import com.itdp.arnd.dto.BalanceData;
import com.itdp.arnd.dto.ReqCreateTransaction;
import com.itdp.arnd.entity.Transactions;
import com.itdp.arnd.service.BankUserService;
import com.itdp.arnd.service.CurrencyService;
import com.itdp.arnd.service.TransactionService;
import com.itdp.arnd.utils.ResponseUtil;

import java.util.List;

@CrossOrigin(origins = "*") 
@RestController
@RequestMapping("/api/v1")
public class ApplicationController {

    @Autowired
    TransactionService transactionService;
    
    @Autowired
    BankUserService bankUserService;

    @Autowired
    CurrencyService currencyService;

    @PostMapping("/createTransaction")
    public ResponseEntity<ApiResponse<Transactions>> createTransaction (@RequestBody ReqCreateTransaction request) {
        Transactions transaction = transactionService.createTransaction(request);
        return ResponseEntity.ok(ResponseUtil.success("success do transaction", transaction)); 
    }

    @GetMapping("/getBalance")
    public ResponseEntity<ApiResponse<BalanceData>> getBalance(@RequestParam String bank_user_id) {
        BalanceData response = bankUserService.getBalance(bank_user_id);
        return ResponseEntity.ok(ResponseUtil.success("Balance retrieved successfully", response));
    }

    @GetMapping("/getRates")
    public ResponseEntity<ApiResponse<List<GetCurrency>>> getRate(){
        List<GetCurrency> currency = currencyService.getRate();

        return ResponseEntity.ok(ResponseUtil.success("currency retrieved successfully", currency));
    }
    
}
