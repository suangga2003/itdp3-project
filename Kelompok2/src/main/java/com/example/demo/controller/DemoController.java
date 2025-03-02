package com.example.demo.controller;

import com.example.demo.entity.Account;
import com.example.demo.entity.Rate;
import com.example.demo.entity.Transaksi;
import com.example.demo.entity.User;
import com.example.demo.model.*;
import com.example.demo.service.AccountService;
import com.example.demo.service.RateService;
import com.example.demo.service.TransaksiService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DemoController {
    @Autowired
    UserService userService;

    @Autowired
    RateService rateService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransaksiService transaksiService;

    @PostMapping("/createUser")
    public ResponseEntity<User> create (@RequestBody User request) {
        User response = request;

        response = userService.createUser(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getUser")
    public ResponseEntity<User> getUser (@RequestParam String param) {
        User response = new User();

        response = userService.getUser(param);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/createKurs")
    public ResponseEntity<Rate> createRate(@RequestBody Rate request){

        Rate response = request;
        response = rateService.createRate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/createAkun")
    public ResponseEntity<Account> createAccount(@RequestBody Account request){
        Account response = request;
        response = accountService.createAccount(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAllAkun")
    public ResponseEntity<AkunDTO> getAllAkun(@RequestParam Integer requestUserId){
        AkunDTO response = accountService.getAllAccount(requestUserId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getRekening")
    public ResponseEntity<Account> getRekening(@RequestParam String noRekening){
        Account response = accountService.getAccountByNorek(noRekening);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/transferValas")
    public ResponseEntity<GetTransferDTO> transfer(@RequestBody ReqTransferDTO reqTransferDTO){
        GetTransferDTO response = transaksiService.transferValas(reqTransferDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/updateKursValas")
    public ResponseEntity<ResponRateUpdateDTO> updateKursValas(@RequestBody UpdateRateDTO request){
        ResponRateUpdateDTO response = new ResponRateUpdateDTO();
        response = rateService.updateRate(request);

        return ResponseEntity.ok(response);
    }

}
