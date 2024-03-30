package com.practice.practice.controllers;

import com.practice.practice.BankService;
import com.practice.practice.BankServiceList;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class ShowController {

    @PostMapping("/show")
    public ResponseEntity<BankService> showService(@RequestParam("uuid") String uuidString, HttpSession session) {
        try {
            UUID uuid = UUID.fromString(uuidString);
            BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
            ResponseEntity<BankService> response = bankServiceList.showService(uuid);

            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                session.setAttribute("message", "404 - Услуга не найдена");
                session.setAttribute("messageType", "error");
                return response;
            } else {
                session.setAttribute("message", "Услуга успешно найдена");
                session.setAttribute("messageType", "success");
                return response;
            }
        } catch (IllegalArgumentException e) {
            session.setAttribute("message", "400 - Неверный формат UUID");
            session.setAttribute("messageType", "error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}