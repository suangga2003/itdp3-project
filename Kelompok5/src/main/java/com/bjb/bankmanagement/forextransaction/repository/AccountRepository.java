package com.bjb.bankmanagement.forextransaction.repository;

import com.bjb.bankmanagement.forextransaction.entity.UserAccounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<UserAccounts, Long> {
    UserAccounts findByAccountNumber(String accountNumber);
    List<UserAccounts> findByUserProfileId(Long userProfileId);
    UserAccounts findByUserProfileIdAndAccountNumber(Long userProfileId, String accountNumber);
}