package com.example.miniprojectjava.service;

import com.example.miniprojectjava.entity.Currency;
import com.example.miniprojectjava.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

    public Currency getCurrencyById(int id) {
        return currencyRepository.findById(id).get();
    }

    public Currency createCurrency(Currency request) {
        Currency response = request;

        currencyRepository.save(response);
        return response;
    }
}
