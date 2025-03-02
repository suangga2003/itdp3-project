package com.forex.exchange.service;

import com.forex.exchange.dto.*;
import com.forex.exchange.entity.ExchangeRate;
import com.forex.exchange.entity.Transaction;
import com.forex.exchange.entity.User;
import com.forex.exchange.repository.ExchangeRateRepository;
import com.forex.exchange.repository.TransactionRepository;
import com.forex.exchange.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final UserRepository userRepository;
    private final ExchangeRateRepository exchangeRateRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(
            UserRepository userRepository,
            ExchangeRateRepository exchangeRateRepository,
            TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.exchangeRateRepository = exchangeRateRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public DepositWithdrawResponseDto deposit(DepositWithdrawDto depositDto) {
        User user = userRepository.findById(depositDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + depositDto.getUserId()));

        String currency = depositDto.getCurrency();
        BigDecimal amount = depositDto.getAmount();

        // Update user balance
        switch (currency) {
            case "IDR":
                user.setIdrBalance(user.getIdrBalance().add(amount));
                break;
            case "USD":
                user.setUsdBalance(user.getUsdBalance().add(amount));
                break;
            case "JPY":
                user.setJpyBalance(user.getJpyBalance().add(amount));
                break;
            default:
                throw new IllegalArgumentException("Unsupported currency: " + currency);
        }

        User updatedUser = userRepository.save(user);

        // Record transaction
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTransactionType("DEPOSIT");
        transaction.setToCurrency(currency);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setNewIdrBalance(updatedUser.getIdrBalance());
        transaction.setNewUsdBalance(updatedUser.getUsdBalance());
        transaction.setNewJpyBalance(updatedUser.getJpyBalance());
        transactionRepository.save(transaction);

        // Prepare response
        DepositWithdrawResponseDto response = new DepositWithdrawResponseDto();
        response.setUserId(user.getId());
        response.setCurrency(currency);
        response.setAmount(amount);

        Map<String, BigDecimal> balances = new HashMap<>();
        balances.put("IDR", updatedUser.getIdrBalance());
        balances.put("USD", updatedUser.getUsdBalance());
        balances.put("JPY", updatedUser.getJpyBalance());
        response.setNewBalance(balances);

        return response;
    }

    @Transactional
    public DepositWithdrawResponseDto withdraw(DepositWithdrawDto withdrawDto) {
        User user = userRepository.findById(withdrawDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + withdrawDto.getUserId()));

        String currency = withdrawDto.getCurrency();
        BigDecimal amount = withdrawDto.getAmount();

        // Check if user has sufficient balance
        switch (currency) {
            case "IDR":
                if (user.getIdrBalance().compareTo(amount) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient IDR balance");
                }
                user.setIdrBalance(user.getIdrBalance().subtract(amount));
                break;
            case "USD":
                if (user.getUsdBalance().compareTo(amount) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient USD balance");
                }
                user.setUsdBalance(user.getUsdBalance().subtract(amount));
                break;
            case "JPY":
                if (user.getJpyBalance().compareTo(amount) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient JPY balance");
                }
                user.setJpyBalance(user.getJpyBalance().subtract(amount));
                break;
            default:
                throw new IllegalArgumentException("Unsupported currency: " + currency);
        }

        User updatedUser = userRepository.save(user);

        // Record transaction
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTransactionType("WITHDRAW");
        transaction.setFromCurrency(currency);
        transaction.setToCurrency(currency);  // Same for withdraw
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setNewIdrBalance(updatedUser.getIdrBalance());
        transaction.setNewUsdBalance(updatedUser.getUsdBalance());
        transaction.setNewJpyBalance(updatedUser.getJpyBalance());
        transactionRepository.save(transaction);

        // Prepare response
        DepositWithdrawResponseDto response = new DepositWithdrawResponseDto();
        response.setUserId(user.getId());
        response.setCurrency(currency);
        response.setAmount(amount);

        Map<String, BigDecimal> balances = new HashMap<>();
        balances.put("IDR", updatedUser.getIdrBalance());
        balances.put("USD", updatedUser.getUsdBalance());
        balances.put("JPY", updatedUser.getJpyBalance());
        response.setNewBalance(balances);

        return response;
    }

    @Transactional
    public ExchangeResponseDto exchange(ExchangeRequestDto exchangeDto) {
        User user = userRepository.findById(exchangeDto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + exchangeDto.getUserId()));

        String fromCurrency = exchangeDto.getFromCurrency();
        String toCurrency = exchangeDto.getToCurrency();
        BigDecimal amount = exchangeDto.getAmount();

        // Check if user has sufficient balance
        checkSufficientBalance(user, fromCurrency, amount);

        // Get exchange rate
        ExchangeRate exchangeRate = exchangeRateRepository
                .findByFromCurrencyAndToCurrency(fromCurrency, toCurrency)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Exchange rate not found for " + fromCurrency + " to " + toCurrency));

        // Calculate converted amount
        BigDecimal rate = exchangeRate.getRate();
        BigDecimal convertedAmount = amount.multiply(rate);

        // Update user balance
        updateUserBalance(user, fromCurrency, toCurrency, amount, convertedAmount);

        User updatedUser = userRepository.save(user);

        // Record transaction
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setTransactionType("EXCHANGE");
        transaction.setFromCurrency(fromCurrency);
        transaction.setToCurrency(toCurrency);
        transaction.setAmount(amount);
        transaction.setConvertedAmount(convertedAmount);
        transaction.setRate(rate);
        transaction.setTimestamp(LocalDateTime.now());
        transaction.setNewIdrBalance(updatedUser.getIdrBalance());
        transaction.setNewUsdBalance(updatedUser.getUsdBalance());
        transaction.setNewJpyBalance(updatedUser.getJpyBalance());
        transactionRepository.save(transaction);

        // Prepare response
        ExchangeResponseDto response = new ExchangeResponseDto();
        response.setUserId(user.getId());
        response.setFromCurrency(fromCurrency);
        response.setToCurrency(toCurrency);
        response.setAmountExchanged(amount);
        response.setConvertedAmount(convertedAmount);
        response.setRate(rate);

        Map<String, BigDecimal> balances = new HashMap<>();
        balances.put("IDR", updatedUser.getIdrBalance());
        balances.put("USD", updatedUser.getUsdBalance());
        balances.put("JPY", updatedUser.getJpyBalance());
        response.setNewBalance(balances);

        return response;
    }

    public List<TransactionHistoryDto> getExchangeHistory(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));

        return transactionRepository.findByUserAndTransactionTypeOrderByTimestampDesc(user, "EXCHANGE")
                .stream()
                .map(this::convertToHistoryDto)
                .collect(Collectors.toList());
    }

    private void checkSufficientBalance(User user, String currency, BigDecimal amount) {
        switch (currency) {
            case "IDR":
                if (user.getIdrBalance().compareTo(amount) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient IDR balance");
                }
                break;
            case "USD":
                if (user.getUsdBalance().compareTo(amount) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient USD balance");
                }
                break;
            case "JPY":
                if (user.getJpyBalance().compareTo(amount) < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient JPY balance");
                }
                break;
            default:
                throw new IllegalArgumentException("Unsupported currency: " + currency);
        }
    }

    private void updateUserBalance(User user, String fromCurrency, String toCurrency,
                                   BigDecimal amount, BigDecimal convertedAmount) {
        // Deduct from source currency
        switch (fromCurrency) {
            case "IDR":
                user.setIdrBalance(user.getIdrBalance().subtract(amount));
                break;
            case "USD":
                user.setUsdBalance(user.getUsdBalance().subtract(amount));
                break;
            case "JPY":
                user.setJpyBalance(user.getJpyBalance().subtract(amount));
                break;
        }

        // Add to target currency
        switch (toCurrency) {
            case "IDR":
                user.setIdrBalance(user.getIdrBalance().add(convertedAmount));
                break;
            case "USD":
                user.setUsdBalance(user.getUsdBalance().add(convertedAmount));
                break;
            case "JPY":
                user.setJpyBalance(user.getJpyBalance().add(convertedAmount));
                break;
        }
    }

    private TransactionHistoryDto convertToHistoryDto(Transaction transaction) {
        TransactionHistoryDto dto = new TransactionHistoryDto();
        dto.setId(transaction.getId());
        dto.setUserId(transaction.getUser().getId());
        dto.setFromCurrency(transaction.getFromCurrency());
        dto.setToCurrency(transaction.getToCurrency());
        dto.setAmountExchanged(transaction.getAmount());
        dto.setConvertedAmount(transaction.getConvertedAmount());
        dto.setRate(transaction.getRate());
        dto.setTimestamp(transaction.getTimestamp());
        return dto;
    }
}