package com.practice.practice.controllers;

import org.springframework.ui.Model;
import com.practice.practice.BankService;
import com.practice.practice.BankServiceList;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddController {


    @PostMapping("/add-service")
    public ResponseEntity<?> addService(@ModelAttribute BankService service,  HttpSession session) {
        BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
        int contract_num = bankServiceList.findMaxContractNum();
        service.setContract_num(contract_num + 1);
        bankServiceList.addService(service);
        return ResponseEntity.ok(bankServiceList.getServices_list());
    }


}
