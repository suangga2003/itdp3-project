package com.itdp.arnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itdp.arnd.entity.Currencies;

public interface CurrencyRepository extends JpaRepository<Currencies, Integer> {
    Currencies findAllById(Integer Id);
}
