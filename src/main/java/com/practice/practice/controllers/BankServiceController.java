package com.practice.practice.controllers;

import org.springframework.ui.Model;
import com.practice.practice.BankService;
import com.practice.practice.BankServiceList;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class BankServiceController {


    @PostMapping("/add-service")
    public ResponseEntity<?> addService(@ModelAttribute BankService service,  HttpSession session, Model model) {
        BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
        bankServiceList.addService(service);
//        model.addAttribute("message", "Сервис успешно добавлен");
//        model.addAttribute("messageType", "success");
        return ResponseEntity.ok(bankServiceList.getServices_list());
    }


}
