package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Transaksi;
import com.example.demo.entity.User;

public interface TransaksiRepository extends JpaRepository<Transaksi, Integer>
{

}