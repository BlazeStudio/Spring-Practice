package com.practice.practice.controllers;

import com.practice.practice.BankService;
import com.practice.practice.BankServiceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BankServiceController {

    private final BankServiceList bankServiceList;

    @Autowired
    public BankServiceController(BankServiceList bankServiceList) {
        this.bankServiceList = bankServiceList;
    }

    @PostMapping("/add-service")
    public ResponseEntity<?> addService(@ModelAttribute BankService service) {
        UUID uuid = UUID.randomUUID();
        bankServiceList.clear();
        bankServiceList.readFromFile("bank.json");
        bankServiceList.addService(service);
        bankServiceList.writeInFile("bank.json");
        return ResponseEntity.ok(bankServiceList.getServices_list());
    }


}
