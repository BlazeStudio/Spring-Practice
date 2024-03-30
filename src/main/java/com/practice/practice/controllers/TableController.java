package com.practice.practice.controllers;

import com.practice.practice.BankService;
import com.practice.practice.BankServiceList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class TableController {

    @GetMapping("/table")
    public ResponseEntity<String> table(Model model, HttpSession session) {
        BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
        String message = (String) session.getAttribute("message");

        if (bankServiceList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Сервисы не найдены");
        }

        if (message != null) {
            String messageType = (String) session.getAttribute("messageType");
            model.addAttribute("message", message);
            model.addAttribute("messageType", messageType);
            session.removeAttribute("message");
            session.removeAttribute("messageType");
        }

        model.addAttribute("list", bankServiceList);
        return ResponseEntity.ok("table"); // assuming "table" is the name of your view
    }
}
