package com.bjb.bankmanagement.forextransaction.repository;

import com.bjb.bankmanagement.forextransaction.entity.UserAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccounts, Integer> {
    boolean existsByCurrencyCode(String currencyCode);
}
