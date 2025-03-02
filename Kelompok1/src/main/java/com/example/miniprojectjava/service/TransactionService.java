package com.example.miniprojectjava.service;

import com.example.miniprojectjava.dto.TransactionRequestDTO;
import com.example.miniprojectjava.dto.TransactionResponseDTO;
import com.example.miniprojectjava.entity.*;
import com.example.miniprojectjava.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public TransactionResponseDTO createTransaction(TransactionRequestDTO request) {
        Optional<Account> senderOpt = accountRepository.findById(request.getAccountFromId());
        Optional<Account> receiverOpt = accountRepository.findById(request.getAccountToId());
        Optional<Status> failedStatus = statusRepository.findById(3);
        Optional<Status> successStatus = statusRepository.findById(1);
        Optional<TransactionType> transactionType = transactionTypeRepository.findById(1);

//        Validasi pengirim dan penerima
        if (senderOpt.isEmpty() || receiverOpt.isEmpty() || failedStatus.isEmpty() || successStatus.isEmpty()) {
            throw new RuntimeException("Invalid Account or Status");
        }

        Account sender = senderOpt.get();
        Account receiver = receiverOpt.get();
        BigDecimal amount = request.getAmount();
//        Pengkondisian bila account penerima dan pengirim sama
        if (sender.getAccountId().equals(receiver.getAccountId())) {
            Transaction transaction = new Transaction();
            transaction.setAccountFrom(sender);
            transaction.setAccountTo(receiver);
            transaction.setAmount(amount);
            transaction.setStatus(failedStatus.get());
            transaction.setCurrency(sender.getCurrency());
            transaction = transactionRepository.save(transaction);
            return new TransactionResponseDTO(
                    transaction.getTransactionId(),
                    transaction.getAccountFrom().getAccountId(),
                    transaction.getAccountTo().getAccountId(),
                    transaction.getCurrency().getCurrencyName(),
                    transaction.getAmount(),
                    transaction.getStatus().getStatusName(),
                    transaction.getCreatedAt()
            );
        }

//        pengkondisian error exchange rate
        BigDecimal finalAmount = amount;
        ExchangeRate rate = null;
        if (!sender.getCurrency().getCurrencyId().equals(receiver.getCurrency().getCurrencyId())) {
            Optional<ExchangeRate> rateOpt = exchangeRateRepository.findByCurrencyFromAndCurrencyTo(sender.getCurrency(), receiver.getCurrency());
            if (rateOpt.isEmpty()) {
                throw new RuntimeException("Exchange rate not found");
            }
            rate = rateOpt.get();
            finalAmount = amount.multiply(rate.getRate());
        }

//        saldo kurang dari nominal transaksi
        if (sender.getBalance().compareTo(amount) < 0) {
            Transaction transaction = new Transaction();
            transaction.setAccountFrom(sender);
            transaction.setAccountTo(receiver);
            transaction.setAmount(amount);
            transaction.setStatus(failedStatus.get());
            transaction.setCurrency(sender.getCurrency());
            transaction = transactionRepository.save(transaction);
            return new TransactionResponseDTO(
                    transaction.getTransactionId(),
                    transaction.getAccountFrom().getAccountId(),
                    transaction.getAccountTo().getAccountId(),
                    transaction.getCurrency().getCurrencyName(),
                    transaction.getAmount(),
                    transaction.getStatus().getStatusName(),
                    transaction.getCreatedAt()
            );
        }

        sender.setBalance(sender.getBalance().subtract(amount));
        receiver.setBalance(receiver.getBalance().add(finalAmount));

        accountRepository.save(sender);
        accountRepository.save(receiver);

        Transaction transaction = new Transaction();
        transaction.setAccountFrom(sender);
        transaction.setAccountTo(receiver);
        transaction.setAmount(amount);
        transaction.setStatus(successStatus.get());
        transaction.setCurrency(sender.getCurrency());
        transaction.setRate(rate);
        transaction.setTransactionType(transactionType.get());
        transaction = transactionRepository.save(transaction);

        return new TransactionResponseDTO(
                transaction.getTransactionId(),
                transaction.getAccountFrom().getAccountId(),
                transaction.getAccountTo().getAccountId(),
                transaction.getCurrency().getCurrencyName(),
                transaction.getAmount(),
                transaction.getStatus().getStatusName(),
                transaction.getCreatedAt()
        );

    }
}
