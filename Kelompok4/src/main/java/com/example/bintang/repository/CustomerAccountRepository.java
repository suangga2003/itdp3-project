package com.example.bintang.repository;

import com.example.bintang.entity.Customer;
import com.example.bintang.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Integer> {
    List<CustomerAccount> findAllByCustomerId(Integer customerId);
    CustomerAccount findByAccount(String param);
}
