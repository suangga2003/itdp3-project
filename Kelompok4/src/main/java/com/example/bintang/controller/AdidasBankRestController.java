package com.example.bintang.controller;

import com.example.bintang.dto.CustomerAccountRequest;
import com.example.bintang.entity.Customer;
import com.example.bintang.entity.CustomerAccount;
import com.example.bintang.entity.ForeignExchangeMarket;
import com.example.bintang.entity.Transaction;
import com.example.bintang.service.CustomerAccountService;
import com.example.bintang.service.CustomerService;
import com.example.bintang.service.ForeignExchangeMarketService;
import com.example.bintang.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AdidasBankRestController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerAccountService customerAccountService;

    @Autowired
    ForeignExchangeMarketService foreignExchangeMarketService;

    @Autowired
    TransactionService transactionService;

    // Creator: Dewi Ilmi Rizqi
    @PostMapping("/createCustomer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer request){
        Customer response = request;
        response = customerService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getCustomer")
    public ResponseEntity<Customer> getCustomer(@RequestParam String param) {
        Customer response = customerService.getCustomer(param);

        return ResponseEntity.ok(response);
    }

    // Creator: Faisal Aulia Ghani
    @PostMapping("/createCustomerAccount")
    public ResponseEntity<CustomerAccount> createCustomerAccount(@RequestBody CustomerAccountRequest request) {
        CustomerAccount customerAccount = customerAccountService.createCustomerAccount(request);
        return ResponseEntity.ok(customerAccount);
    }

    @GetMapping("/getAllCustomerAccountByCustomerId")
    public ResponseEntity<List<CustomerAccountRequest>> getAllCustomerAccountByCustomerId(@RequestParam Integer param) {
        List<CustomerAccountRequest> response = customerAccountService.getAllByCustomerId(param);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getBalanceByCustomerAccount")
    public ResponseEntity<CustomerAccount> getBalanceByCustomerAccount(@RequestParam String param) {
        CustomerAccount response = customerAccountService.getBalanceByCustomerAccount(param);

        return ResponseEntity.ok(response);
    }

    // Creator: Hafizh Daffa Septianto
    @PostMapping ("/createForeignExchangeMarket")
    public ResponseEntity<ForeignExchangeMarket> addExchangeMarket(@RequestBody ForeignExchangeMarket market) {
        ForeignExchangeMarket createdMarket = foreignExchangeMarketService.addExchangeMarket(market);
        return ResponseEntity.ok(createdMarket);
    }

    @GetMapping("/getForeignExchangeMarket")
    public ResponseEntity<ForeignExchangeMarket> getExchangePrice(@RequestParam String currencyTo) {
        ForeignExchangeMarket exchangePrice = foreignExchangeMarketService.getExchangePrice(currencyTo);

        if (exchangePrice != null) {
            return ResponseEntity.ok(exchangePrice);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updateCustomer")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer request){
        Customer response = new Customer();

        //get user data
        response = customerService.updateCustomer(request);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/updateCustomerAccount")
    public ResponseEntity<CustomerAccount> updateCustomerAccount(@RequestBody CustomerAccount request){
        CustomerAccount response = new CustomerAccount();

        //get user data
        response = customerAccountService.updateCustomerAccount(request);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/depositCustomerAccount")
    public ResponseEntity<CustomerAccount> depositCustomerAccount(@RequestParam String account, @RequestParam Float addBalance){
        CustomerAccount response = new CustomerAccount();

        //get user data
        response = customerAccountService.addBalance(account, addBalance);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/updateForeignExchangeMarket")
    public ResponseEntity<ForeignExchangeMarket> updateForeignExchangeMarket(@RequestBody ForeignExchangeMarket request){
        ForeignExchangeMarket response = new ForeignExchangeMarket();

        //get user data
        response = foreignExchangeMarketService.updateForeignExchangeMarket(request);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/updateTransaction")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction request){
        Transaction response = new Transaction();

        //get user data
        response = transactionService.updateTransaction(request);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/deleteCustomer")
    public ResponseEntity<Customer> deleteCustomer (@RequestParam String param) {
        Customer delCustomer = new Customer();

        delCustomer = customerService.deleteUserById(param);
        return ResponseEntity.ok(delCustomer);
    }

    @DeleteMapping("/deleteCustomerAccount")
    public ResponseEntity<CustomerAccount> deleteCustomerAccount (@RequestParam String param) {
        CustomerAccount delCustomerAccount = new CustomerAccount();

        delCustomerAccount = customerAccountService.deleteUserById(param);
        return ResponseEntity.ok(delCustomerAccount);
    }

    @DeleteMapping("/deleteForeignMarket")
    public ResponseEntity<ForeignExchangeMarket> deleteMarket (@RequestParam String param) {
        ForeignExchangeMarket delMarket = new ForeignExchangeMarket();

        delMarket = foreignExchangeMarketService.deleteUserById(param);
        return ResponseEntity.ok(delMarket);
    }

    @DeleteMapping("/deleteTransaction")
    public ResponseEntity<Transaction> deleteTransaction (@RequestParam String param) {
        Transaction delTransaction = new Transaction();

        delTransaction = transactionService.deleteUserById(param);
        return ResponseEntity.ok(delTransaction);
    }

    // Creator: Bintang Samudro
    @GetMapping("/getAllTransactionByCustomerId")
    public ResponseEntity<List<Transaction>> getAllTransactionByCustomerId(@RequestParam Integer param) {
        List<Transaction> response = transactionService.getAllByCustomerId(param);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/createTransactionExchange")
    public ResponseEntity<?> createTransactionExchange(@RequestBody Transaction request) {
        try {
            Transaction response = transactionService.createTransaction(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Terjadi kesalahan pada server");
        }
    }
}
