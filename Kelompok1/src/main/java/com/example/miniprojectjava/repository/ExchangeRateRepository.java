package com.example.miniprojectjava.repository;

import com.example.miniprojectjava.entity.Currency;
import com.example.miniprojectjava.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
    Optional<ExchangeRate> findByCurrencyFromAndCurrencyTo(Currency currencyFrom, Currency currencyTo);
}
