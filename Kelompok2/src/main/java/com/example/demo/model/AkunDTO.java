package com.example.demo.model;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class AkunDTO {

    private Integer id;

    private String name;

    private String email;

    private List<Account> accounts;
}
