package com.example.miniprojectjava.repository;

import com.example.miniprojectjava.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findAllByUser_UserId(int userId);
}
