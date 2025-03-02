package com.itdp.arnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itdp.arnd.entity.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
    
}
