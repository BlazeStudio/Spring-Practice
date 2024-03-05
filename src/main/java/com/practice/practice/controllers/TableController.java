package com.practice.practice.controllers;

import com.practice.practice.BankService;
import com.practice.practice.BankServiceList;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TableController {

    @GetMapping("/table")
    public String home(Model model) {
        BankService service1 = new BankService(1, "Сервис 1", 29022024, "Тип 1", 100.0, 0);
        BankService service2 = new BankService(2, "Сервис 2", 29022024, "Тип 2", 150.0, 50);
//        UUID test = service1.getUuid();
        BankServiceList serviceList = new BankServiceList();
        serviceList.addService(service1);
        serviceList.addService(service2);
        serviceList.writeInFile("bank.json");
        BankServiceList serviceList2 = new BankServiceList();
        serviceList2.readFromFile("bank.json");
        model.addAttribute("list", serviceList2);
        return "table";
    }


}
