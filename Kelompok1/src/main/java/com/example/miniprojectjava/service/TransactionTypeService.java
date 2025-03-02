package com.example.miniprojectjava.service;

import com.example.miniprojectjava.entity.TransactionType;
import com.example.miniprojectjava.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionTypeService {

    @Autowired
    private TransactionTypeRepository transactionTypeRepository;

    public List<TransactionType> getAllTransactionType() {
        return transactionTypeRepository.findAll();
    }

    public TransactionType getTransactionTypeById(int id) {
        return transactionTypeRepository.findById(id).get();
    }

    public TransactionType createTransactionType(TransactionType request) {
        TransactionType response = request;

        transactionTypeRepository.save(response);
        return response;
    }
}
