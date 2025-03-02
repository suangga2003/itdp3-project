package com.example.miniprojectjava.repository;

import com.example.miniprojectjava.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
