package com.example.miniprojectjava.controller;

import com.example.miniprojectjava.dto.LoginRequestDTO;
import com.example.miniprojectjava.dto.UserRequestDTO;
import com.example.miniprojectjava.entity.User;
import com.example.miniprojectjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser() { return userService.getAllUser();}

    @GetMapping("/getUser")
    public User getUserById(int id) { return userService.getUserById(id);}

    @PostMapping("/createUser")
    public ResponseEntity<UserRequestDTO> create (@RequestBody UserRequestDTO request) {
        UserRequestDTO response = request;

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        userService.createUser(user);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request) {

        return userService.login(request.getUsername(), request.getPassword());
    }
}
