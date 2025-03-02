package com.example.bintang.repository;

import com.example.bintang.entity.CustomerAccount;
import com.example.bintang.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByCustomerId(Integer customerId);
}
