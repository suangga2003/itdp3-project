package com.example.miniprojectjava.service;

import com.example.miniprojectjava.entity.User;
import com.example.miniprojectjava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).get();
    }

    public User createUser(User request) {
        User response = request;

        userRepository.save(response);
        return response;
    }

    public String login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {
                return "Login Berhasil!";
            } else {
                return "Password Salah!";
            }
        }
        return "Username Tidak Ditemukan!";
    }
}
