package com.bjb.bankmanagement.forextransaction.repository;

import com.bjb.bankmanagement.forextransaction.entity.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRates, Long> {
    ExchangeRates findByFromCurrencyCodeAndToCurrencyCode(String fromCurrencyCode, String toCurrencyCode);
}
