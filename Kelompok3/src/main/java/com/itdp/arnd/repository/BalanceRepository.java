package com.itdp.arnd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itdp.arnd.entity.BankBalances;

public interface BalanceRepository extends JpaRepository<BankBalances, Integer> {
    BankBalances findByBankUserIdAndCurrencyId(Integer bankUserId, Integer currencyId);
    List<BankBalances> findAllById(Integer Id);
    List<BankBalances> findAllByBankUserId(Integer bankUserId);
}
