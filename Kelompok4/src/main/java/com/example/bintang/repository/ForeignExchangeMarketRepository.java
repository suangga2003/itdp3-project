package com.example.bintang.repository;

import com.example.bintang.entity.ForeignExchangeMarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForeignExchangeMarketRepository extends JpaRepository<ForeignExchangeMarket, Integer> {
    ForeignExchangeMarket findByCurrencyTo(String currencyTo);
}
