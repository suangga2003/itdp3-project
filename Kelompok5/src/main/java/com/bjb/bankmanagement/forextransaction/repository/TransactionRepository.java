package com.bjb.bankmanagement.forextransaction.repository;

import com.bjb.bankmanagement.forextransaction.entity.TransactionHistories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionHistories, Long> {
    List<TransactionHistories> findByFromUserAccountId(Long userAccountId);
}
