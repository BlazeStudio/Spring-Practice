package com.practice.practice.controllers;

import com.practice.practice.BankServiceList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class DeleteController {

    private final BankServiceList bankServiceList;

    @Autowired
    public DeleteController(BankServiceList bankServiceList) {
        this.bankServiceList = bankServiceList;
    }

    @PostMapping("/delete")
    public String deleteService(@RequestParam("uuid") UUID uuid, Model model) {
        bankServiceList.clear();
        bankServiceList.readFromFile("bank.json");
        bankServiceList.deleteService(uuid);
        bankServiceList.writeInFile("bank.json");
        model.addAttribute("successMessage", "Услуга успешно удалена");
        return "redirect:/table";
    }
}

