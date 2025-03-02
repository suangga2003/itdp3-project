package com.forex.exchange.controller;

import com.forex.exchange.dto.ApiResponse;
import com.forex.exchange.dto.CreateUserDto;
import com.forex.exchange.dto.UserDto;
import com.forex.exchange.service.UserService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", user));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDto>> createUser(@RequestBody CreateUserDto createUserDto) {
        UserDto user = userService.createUser(createUserDto);
        return new ResponseEntity<>(
                ApiResponse.success("User created successfully", user),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> updateUser(
            @PathVariable Long id,
            @RequestBody CreateUserDto updateUserDto) {
        UserDto user = userService.updateUser(id, updateUserDto);
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
    }
}