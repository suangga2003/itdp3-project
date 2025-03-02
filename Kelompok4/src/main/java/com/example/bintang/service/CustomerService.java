package com.example.bintang.service;

import com.example.bintang.entity.Customer;
import com.example.bintang.entity.CustomerAccount;
import com.example.bintang.repository.CustomerAccountRepository;
import com.example.bintang.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer createUser(Customer request){
        Customer response = request;
        customerRepository.save(response);
        return response;
    }

    public Customer getCustomer(String param) {
        Customer user = customerRepository.findById(Integer.valueOf(param)).get();

        return user;
    }

    @Transactional
    public Customer updateCustomer(Customer param) {
        Customer response = new Customer();

        //get user data
        response = customerRepository.findById(param.getId()).get();

        //set user address
        response.setAddress(param.getAddress());

        //save to db
        customerRepository.save(response);
        return response;
    }

    public Customer deleteUserById(String param) {

        try{
            customerRepository.deleteById(Integer.valueOf(param));

            if(customerRepository.findById(Integer.valueOf(param)).isPresent()){
                Customer response = new Customer();
                response.setRc("0005");
                response.setRcDesc("No data with user id " + param);
                return response;
            }

            Customer response = new Customer();
            response.setRc("0000");
            response.setRcDesc("Successfully delete user id");

            return response;

        } catch (Exception e) {
            Customer response = new Customer();
            response.setRc("0005");
            response.setRcDesc("Failed to delete user id");
            return response;
        }
    }
}
