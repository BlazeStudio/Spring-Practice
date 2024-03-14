package com.practice.practice.controllers;

import com.practice.practice.BankServiceList;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Controller
public class DeleteController {


    @PostMapping("/delete")
    public String deleteService(@RequestParam("uuid") UUID uuid, Model model,  HttpSession session) {
        BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
        bankServiceList.deleteService(uuid);
        String encodedMessage = URLEncoder.encode("Сервис успешно удалён", StandardCharsets.UTF_8);
        String redirectUrl = "/table?message=" + encodedMessage + "&type=success";
        return "redirect:" + redirectUrl;
    }
}

