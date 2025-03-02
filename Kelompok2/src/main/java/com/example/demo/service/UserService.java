package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.AccountsRepository;
import com.example.demo.repository.TransaksiRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User request) {
        User response = request;

        userRepository.save(response);

        return response;
    }

    public User getUser(String param) {
        try{
            User response = new User();

            response = userRepository.findById(Integer.valueOf(param)).get();

            response.setRc("0000");
            response.setRcDesc("Success");

            return response;
        } catch (Exception e){
            User response = new User();

            response.setRc("4444");
            response.setRcDesc("ID tidak dikenal!");

            return response;
        }
    }
}
