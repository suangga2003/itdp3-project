package com.forex.exchange.service;

import com.forex.exchange.dto.CreateUserDto;
import com.forex.exchange.dto.UserDto;
import com.forex.exchange.entity.User;
import com.forex.exchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
        return convertToDto(user);
    }

    @Transactional
    public UserDto createUser(CreateUserDto createUserDto) {
        if (userRepository.existsByEmail(createUserDto.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use: " + createUserDto.getEmail());
        }

        User user = new User();
        user.setName(createUserDto.getName());
        user.setEmail(createUserDto.getEmail());
        user.setAddress(createUserDto.getAddress());
        user.setGender(createUserDto.getGender());
        user.setIdrBalance(BigDecimal.valueOf(500000));
        user.setUsdBalance(BigDecimal.valueOf(100));
        user.setJpyBalance(BigDecimal.valueOf(10000));

        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }

    @Transactional
    public UserDto updateUser(Long id, CreateUserDto updateUserDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));

        user.setName(updateUserDto.getName());
        user.setEmail(updateUserDto.getEmail());
        user.setAddress(updateUserDto.getAddress());
        user.setGender(updateUserDto.getGender());

        User updatedUser = userRepository.save(user);
        return convertToDto(updatedUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        dto.setGender(user.getGender());

        Map<String, BigDecimal> balance = new HashMap<>();
        balance.put("IDR", user.getIdrBalance());
        balance.put("USD", user.getUsdBalance());
        balance.put("JPY", user.getJpyBalance());
        dto.setBalance(balance);

        return dto;
    }
}