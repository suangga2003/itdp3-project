package com.example.miniprojectjava.repository;

import com.example.miniprojectjava.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
