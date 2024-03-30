package com.practice.practice.controllers;

import com.practice.practice.BankServiceList;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class DeleteController {


    @PostMapping("/delete")
    public ResponseEntity<String> deleteService(@RequestParam("uuid") String uuidString, Model model, HttpSession session) {
        try {
            UUID uuid = UUID.fromString(uuidString);
            BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
            ResponseEntity<ResponseEntity<String>> responseEntity = bankServiceList.deleteService(String.valueOf(uuid));

            if (responseEntity.getBody().getStatusCode() == HttpStatus.NOT_FOUND) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 - Услуга не найдена");
            } else if (responseEntity.getBody().getStatusCode() == HttpStatus.BAD_REQUEST) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 - Некорректные данные");
            } else {
                return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "/table").body(null);
            }
        } catch (IllegalArgumentException e) {
            session.setAttribute("message", "400 - Некорректный формат UUID");
            session.setAttribute("messageType", "error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("400 - Некорректный формат UUID");
        }
    }


//    @PostMapping("/delete")
//    public String deleteService(@RequestParam("uuid") UUID uuid, Model model,  HttpSession session) {
//        BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
//        ResponseEntity<String> response = bankServiceList.deleteService(uuid);
//        return "redirect:/table";
//    }
}

