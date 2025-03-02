package com.example.bintang.service;

import com.example.bintang.entity.Customer;
import com.example.bintang.entity.CustomerAccount;
import com.example.bintang.entity.ForeignExchangeMarket;
import com.example.bintang.entity.Transaction;
import com.example.bintang.repository.CustomerAccountRepository;
import com.example.bintang.repository.CustomerRepository;
import com.example.bintang.repository.ForeignExchangeMarketRepository;
import com.example.bintang.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ForeignExchangeMarketRepository foreignExchangeMarketRepository;

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    public Transaction createTransaction(Transaction request) {

        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Jumlah transfer harus lebih dari 0");
        }

        Transaction response = request;
        ForeignExchangeMarket foreignExchangeMarketRequest = foreignExchangeMarketRepository.findByCurrencyTo(request.getToCurrency());
        CustomerAccount customerAccountFrom = customerAccountRepository.findByAccount(request.getFromAccount());
        CustomerAccount customerAccountTo = customerAccountRepository.findByAccount(request.getToAccount());
        Customer customerRequest = customerAccountFrom.getCustomer();

        if (foreignExchangeMarketRequest == null) {
            throw new IllegalArgumentException("Nilai tukar untuk mata uang " + request.getToCurrency() + " tidak ditemukan");
        }

        Float fromAccountBalance = Float.parseFloat(customerAccountFrom.getBalance());

        if (customerAccountTo == null) {
            throw new IllegalArgumentException("Rekening tujuan tidak ditemukan");
        }

        if (fromAccountBalance < request.getAmount()) {
            throw new IllegalArgumentException("Saldo tidak mencukupi untuk transaksi");
        }

        Float result = response.getAmount() / foreignExchangeMarketRequest.getExchangePrice();

        response.setCustomer(customerRequest);
        response.setExchangeRate(foreignExchangeMarketRequest);
        response.setExchangeRateAtTransaction(foreignExchangeMarketRequest.getExchangePrice());
        response.setConvertedAmount(response.getAmount() / foreignExchangeMarketRequest.getExchangePrice());
        response.setStatus("SUCCESS");

        Float fromAccountBalanceAfter = Float.parseFloat(customerAccountFrom.getBalance()) - request.getAmount();
        Float toAccountBalanceAfter = Float.parseFloat(customerAccountTo.getBalance()) + result;

        response.setFromAccountBalanceAfter(fromAccountBalanceAfter);
        response.setToAccountBalanceAfter(toAccountBalanceAfter);

        customerAccountFrom.setBalance(String.valueOf(fromAccountBalanceAfter));
        customerAccountTo.setBalance(String.valueOf(toAccountBalanceAfter));

        transactionRepository.save(response);
        customerAccountRepository.save(customerAccountFrom);
        customerAccountRepository.save(customerAccountTo);
        return response;
    }

    public List<Transaction> getAllByCustomerId(Integer param) {
        List<Transaction> response = transactionRepository.findAllByCustomerId(param);

        return response;
    }

    @Transactional
    public Transaction updateTransaction(Transaction param) {
        Transaction response = new Transaction();

        //get user data
        response = transactionRepository.findById(param.getId()).get();

        //set user address
        response.setAmount(param.getAmount());
        response.setExchangeRate(param.getExchangeRate());
        //save to db
        transactionRepository.save(response);
        return response;
    }

    public Transaction deleteUserById(String param) {

        try{
            transactionRepository.deleteById(Integer.valueOf(param));

            if(transactionRepository.findById(Integer.valueOf(param)).isPresent()){
                Transaction response = new Transaction();
                response.setRc("0005");
                response.setRcDesc("No data with user id " + param);
                return response;
            }

            Transaction response = new Transaction();
            response.setRc("0000");
            response.setRcDesc("Successfully delete user id");

            return response;

        } catch (Exception e) {
            Transaction response = new Transaction();
            response.setRc("0005");
            response.setRcDesc("Failed to delete user id");
            return response;
        }
    }
}
