package com.forex.exchange.service;

import com.forex.exchange.dto.CreateExchangeRateDto;
import com.forex.exchange.dto.ExchangeRateDto;
import com.forex.exchange.entity.ExchangeRate;
import com.forex.exchange.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ExchangeRateService(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    public List<ExchangeRateDto> getAllRates() {
        return exchangeRateRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ExchangeRateDto getRateById(Long id) {
        ExchangeRate rate = exchangeRateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange rate not found with id: " + id));
        return convertToDto(rate);
    }

    @Transactional
    public ExchangeRateDto createRate(CreateExchangeRateDto rateDto) {
        exchangeRateRepository.findByFromCurrencyAndToCurrency(
                        rateDto.getFromCurrency(), rateDto.getToCurrency())
                .ifPresent(r -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT,"Exchange rate already exists for " + rateDto.getFromCurrency() + " to " + rateDto.getToCurrency());
                });

        ExchangeRate rate = new ExchangeRate();
        rate.setFromCurrency(rateDto.getFromCurrency());
        rate.setToCurrency(rateDto.getToCurrency());
        rate.setRate(rateDto.getRate());

        ExchangeRate savedRate = exchangeRateRepository.save(rate);
        return convertToDto(savedRate);
    }

    @Transactional
    public ExchangeRateDto updateRate(Long id, ExchangeRateDto rateDto) {
        ExchangeRate rate = exchangeRateRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange rate not found with id: " + id));

        rate.setFromCurrency(rateDto.getFromCurrency());
        rate.setToCurrency(rateDto.getToCurrency());
        rate.setRate(rateDto.getRate());

        ExchangeRate updatedRate = exchangeRateRepository.save(rate);
        return convertToDto(updatedRate);
    }

    @Transactional
    public void deleteRate(Long id) {
        if (!exchangeRateRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange rate not found with id: " + id);
        }
        exchangeRateRepository.deleteById(id);
    }

    private ExchangeRateDto convertToDto(ExchangeRate rate) {
        ExchangeRateDto dto = new ExchangeRateDto();
        dto.setId(rate.getId());
        dto.setFromCurrency(rate.getFromCurrency());
        dto.setToCurrency(rate.getToCurrency());
        dto.setRate(rate.getRate());
        return dto;
    }
}