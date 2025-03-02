package com.itdp.arnd.service;

import com.itdp.arnd.dto.GetCurrency;
import com.itdp.arnd.entity.Currencies;
import com.itdp.arnd.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public List<GetCurrency> getRate(){
        List<Currencies> currencies = currencyRepository.findAll();

        return currencies.stream()
                .map(currency -> {
                    GetCurrency dto = new GetCurrency();
                    dto.setId(currency.getId());
                    dto.setName(currency.getName());
                    dto.setCode(currency.getCode());
                    dto.setCurrencyRate(currency.getCurrencyRate());
                    dto.setUpdatedAt(currency.getUpdatedAt());
                    dto.setIsPrimary(currency.getIsPrimary());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
