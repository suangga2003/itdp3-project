package com.forex.exchange.repository;

import com.forex.exchange.entity.Transaction;
import com.forex.exchange.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUserOrderByTimestampDesc(User user);
    List<Transaction> findByUserAndTransactionTypeOrderByTimestampDesc(User user, String transactionType);
}