package com.example.demo.repository;

import com.example.demo.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Integer> {
    Rate findByMataUangAsalAndMataUangTujuan(String mataUangAsal, String mataUangTujuan);

}
