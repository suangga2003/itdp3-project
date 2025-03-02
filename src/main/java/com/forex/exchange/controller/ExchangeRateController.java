package com.forex.exchange.controller;

import com.forex.exchange.dto.ApiResponse;
import com.forex.exchange.dto.CreateExchangeRateDto;
import com.forex.exchange.dto.ExchangeRateDto;
import com.forex.exchange.service.ExchangeRateService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rates")
public class ExchangeRateController {


    @Autowired
    ExchangeRateService exchangeRateService;


    @GetMapping
    public ResponseEntity<ApiResponse<List<ExchangeRateDto>>> getAllRates() {
        List<ExchangeRateDto> rates = exchangeRateService.getAllRates();
        return ResponseEntity.ok(ApiResponse.success("Exchange rates retrieved successfully", rates));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ExchangeRateDto>> getRateById(@PathVariable Long id) {
        ExchangeRateDto rate = exchangeRateService.getRateById(id);
        return ResponseEntity.ok(ApiResponse.success("Exchange rate retrieved successfully", rate));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ExchangeRateDto>> createRate(@RequestBody CreateExchangeRateDto rateDto) {
        ExchangeRateDto rate = exchangeRateService.createRate(rateDto);
        return new ResponseEntity<>(
                ApiResponse.success("Exchange rate created successfully", rate),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ExchangeRateDto>> updateRate(
            @PathVariable Long id,
            @RequestBody ExchangeRateDto rateDto) {
        ExchangeRateDto rate = exchangeRateService.updateRate(id, rateDto);
        return ResponseEntity.ok(ApiResponse.success("Exchange rate updated successfully", rate));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRate(@PathVariable Long id) {
        exchangeRateService.deleteRate(id);
        return ResponseEntity.ok(ApiResponse.success("Exchange rate deleted successfully", null));
    }
}