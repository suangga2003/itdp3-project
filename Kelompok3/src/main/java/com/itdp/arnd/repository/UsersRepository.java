package com.itdp.arnd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itdp.arnd.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findAllById(Integer Id);
}
