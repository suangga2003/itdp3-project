package com.example.miniprojectjava.controller;

import com.example.miniprojectjava.dto.StatusRequestDTO;
import com.example.miniprojectjava.entity.Status;
import com.example.miniprojectjava.entity.TransactionType;
import com.example.miniprojectjava.entity.User;
import com.example.miniprojectjava.service.StatusService;
import com.example.miniprojectjava.service.TransactionTypeService;
import com.example.miniprojectjava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/status")
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping
    public List<Status> getAllStatus() { return statusService.getAllStatus();}

    @GetMapping("/getStatus")
    public Status getStatusById(int id) { return statusService.getStatusById(id);}

    @PostMapping("/createStatus")
    public ResponseEntity<StatusRequestDTO> create (@RequestBody StatusRequestDTO request) {
        StatusRequestDTO response = request;

        Status status = new Status();
        status.setStatusName(request.getStatusName());

        statusService.createStatus(status);

        return ResponseEntity.ok(response);
    }
}
