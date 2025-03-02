package com.example.bintang.service;

import com.example.bintang.dto.CustomerAccountRequest;
import com.example.bintang.entity.Customer;
import com.example.bintang.entity.CustomerAccount;
import com.example.bintang.repository.CustomerAccountRepository;
import com.example.bintang.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerAccountService {

    @Autowired
    CustomerAccountRepository customerAccountRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public CustomerAccount createCustomerAccount(CustomerAccountRequest request) {
        // Cek apakah customer ada
        Optional<Customer> customerOpt = customerRepository.findById(request.getCustomerId());
        if (customerOpt.isEmpty()) {
            throw new IllegalArgumentException("Customer dengan ID " + request.getCustomerId() + " tidak ditemukan.");
        }

        CustomerAccount customerAccount = new CustomerAccount();
        customerAccount.setCustomer(customerOpt.get());
        customerAccount.setType(request.getType());
        customerAccount.setAccount(request.getAccount());
        customerAccount.setBalance(String.valueOf(request.getBalance()));

        customerAccountRepository.save(customerAccount);

        return customerAccount;

    }

    public List<CustomerAccountRequest> getAllByCustomerId(Integer param) {
        List<CustomerAccount> response = customerAccountRepository.findAllByCustomerId(param);
        List<CustomerAccountRequest> responseDTO = new ArrayList<>();

        for (CustomerAccount account : response) {
            CustomerAccountRequest dto = new CustomerAccountRequest();
            dto.setCustomerId(account.getCustomer().getId());
            dto.setAccount(account.getAccount());
            dto.setType(account.getType());
            dto.setBalance(Float.valueOf(account.getBalance()));

            responseDTO.add(dto);
        }
        return responseDTO;
    }

    public CustomerAccount getBalanceByCustomerAccount(String param) {
        CustomerAccount response = customerAccountRepository.findByAccount(param);

        return response;
    }

    public CustomerAccount addBalance(String account, Float balance) {
        CustomerAccount response = customerAccountRepository.findByAccount(account);

        response.setBalance(String.valueOf(Float.parseFloat(response.getBalance()) + balance));
        customerAccountRepository.save(response);
        return response;
    }

    @Transactional
    public CustomerAccount updateCustomerAccount(CustomerAccount param) {
        CustomerAccount response = new CustomerAccount();

        //get user data
        response = customerAccountRepository.findById(param.getId()).get();

        //set user address
        response.setBalance(param.getBalance());

        //save to db
        customerAccountRepository.save(response);
        return response;
    }

    public CustomerAccount deleteUserById(String param) {

        try{
            customerAccountRepository.deleteById(Integer.valueOf(param));

            if(customerAccountRepository.findById(Integer.valueOf(param)).isPresent()){
                CustomerAccount response = new CustomerAccount();
                response.setRc("0005");
                response.setRcDesc("No data with user id " + param);
                return response;
            }

            CustomerAccount response = new CustomerAccount();
            response.setRc("0000");
            response.setRcDesc("Successfully delete user id");

            return response;

        } catch (Exception e) {
            CustomerAccount response = new CustomerAccount();
            response.setRc("0005");
            response.setRcDesc("Failed to delete user id");
            return response;
        }
    }
}
