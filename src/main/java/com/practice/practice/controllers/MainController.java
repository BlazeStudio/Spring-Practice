package com.practice.practice.controllers;

import com.practice.practice.BankService;
import com.practice.practice.BankServiceList;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
        String message = (String) session.getAttribute("message");
        if (bankServiceList != null) {
            session.removeAttribute("bankServiceList");
        }
        if (message != null) {
            String messageType = (String) session.getAttribute("messageType");
            model.addAttribute("message", message);
            model.addAttribute("messageType", messageType);
            session.removeAttribute("message");
            session.removeAttribute("messageType");
        }
        model.addAttribute("title", "Hello World!");
        return "home";
    }

}