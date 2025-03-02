package com.example.miniprojectjava.controller;

import com.example.miniprojectjava.dto.ExchangeRateRequestDTO;
import com.example.miniprojectjava.entity.ExchangeRate;
import com.example.miniprojectjava.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/exchange-rate")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping
    public List<ExchangeRate> getAllExchangeRate() { return exchangeRateService.getAllExchangeRate();}

    @GetMapping("/getExchangeRate")
    public ResponseEntity<ExchangeRate> getExchangeRateById(int id) {
        return ResponseEntity.ok(exchangeRateService.getExchangeRateById(id));
    }

    @PostMapping("/createExchangeRate")
    public ResponseEntity<?> createExchangeRate(@RequestBody ExchangeRateRequestDTO request) {
        ExchangeRate response = exchangeRateService.createExchangeRate(request);
        return ResponseEntity.ok(response);
    }
}
