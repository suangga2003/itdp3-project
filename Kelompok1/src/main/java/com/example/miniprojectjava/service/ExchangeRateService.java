package com.example.miniprojectjava.service;

import com.example.miniprojectjava.dto.ExchangeRateRequestDTO;
import com.example.miniprojectjava.entity.Currency;
import com.example.miniprojectjava.entity.ExchangeRate;
import com.example.miniprojectjava.repository.CurrencyRepository;
import com.example.miniprojectjava.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<ExchangeRate> getAllExchangeRate() { return exchangeRateRepository.findAll();}
    public ExchangeRate getExchangeRateById(int id) { return exchangeRateRepository.findById(id).get();}

    public ExchangeRate createExchangeRate(ExchangeRateRequestDTO request) {
        Currency currencyFrom = currencyRepository.findById(request.getCurrencyFromId()).orElseThrow(() -> new RuntimeException("Currency not found"));
        Currency currencyTo = currencyRepository.findById(request.getCurrencyToId()).orElseThrow(() -> new RuntimeException("Currency not found"));

        if (currencyFrom.equals(currencyTo)) {
            throw new RuntimeException("Currency from and to are same!");
        }

        Optional<ExchangeRate> existingRate = exchangeRateRepository.findByCurrencyFromAndCurrencyTo(currencyFrom, currencyTo);
        if (existingRate.isPresent()) {
            throw new RuntimeException("Exchange Rate already exists!");
        }

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCurrencyFrom(currencyFrom);
        exchangeRate.setCurrencyTo(currencyTo);
        exchangeRate.setRate(request.getRate());

        return exchangeRateRepository.save(exchangeRate);
    }
}
