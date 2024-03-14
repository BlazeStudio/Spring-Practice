package com.practice.practice.controllers;

import com.practice.practice.BankService;
import com.practice.practice.BankServiceList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class TableController {

    @GetMapping("/table")
    public String table(@RequestParam(name = "message", required = false) String message,
                        @RequestParam(name = "type", required = false) String messageType,Model model, HttpSession session) {
        BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
        if (bankServiceList == null){
            return "redirect:/";
        }
        if (message != null && messageType != null) {
            model.addAttribute("message", message);
            model.addAttribute("messageType", messageType);
        }

        model.addAttribute("list", bankServiceList);
        return "table";
    }


}
