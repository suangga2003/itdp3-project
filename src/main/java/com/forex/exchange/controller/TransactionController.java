package com.forex.exchange.controller;

import com.forex.exchange.dto.*;
import com.forex.exchange.service.TransactionService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionController {


    @Autowired
    TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse<DepositWithdrawResponseDto>> deposit(
            @RequestBody DepositWithdrawDto depositDto) {
        DepositWithdrawResponseDto result = transactionService.deposit(depositDto);
        return ResponseEntity.ok(ApiResponse.success("Deposit successful", result));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<DepositWithdrawResponseDto>> withdraw(
            @RequestBody DepositWithdrawDto withdrawDto) {
        DepositWithdrawResponseDto result = transactionService.withdraw(withdrawDto);
        return ResponseEntity.ok(ApiResponse.success("Withdrawal successful", result));
    }

    @PostMapping("/exchange")
    public ResponseEntity<ApiResponse<ExchangeResponseDto>> exchange(
            @RequestBody ExchangeRequestDto exchangeDto) {
        ExchangeResponseDto result = transactionService.exchange(exchangeDto);
        return ResponseEntity.ok(ApiResponse.success("Exchange successful", result));
    }

    @GetMapping("/users/{id}/exchange-history")
    public ResponseEntity<ApiResponse<List<TransactionHistoryDto>>> getExchangeHistory(@PathVariable Long id) {
        List<TransactionHistoryDto> history = transactionService.getExchangeHistory(id);
        return ResponseEntity.ok(ApiResponse.success("Exchange history retrieved successfully", history));
    }
}