package com.example.miniprojectjava.service;

import com.example.miniprojectjava.entity.Status;
import com.example.miniprojectjava.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public Status getStatusById(int id) {
        return statusRepository.findById(id).get();
    }

    public Status createStatus(Status request) {
        Status response = request;

        statusRepository.save(response);
        return response;
    }
}
