package com.practice.practice.controllers;

import com.practice.practice.BankService;
import com.practice.practice.BankServiceList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Hello World!");
        return "home";
    }

}