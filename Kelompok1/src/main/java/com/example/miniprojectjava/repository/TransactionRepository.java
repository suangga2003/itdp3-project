package com.example.miniprojectjava.repository;

import com.example.miniprojectjava.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
