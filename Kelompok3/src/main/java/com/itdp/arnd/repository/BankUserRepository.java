package com.itdp.arnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itdp.arnd.entity.BankUsers;

public interface BankUserRepository extends JpaRepository<BankUsers, Integer> {
    BankUsers findAllByUserId(Integer userId);
}
