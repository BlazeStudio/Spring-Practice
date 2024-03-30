package com.practice.practice.controllers;

import com.practice.practice.BankServiceList;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.*;

@Controller
public class SaveController {

    @GetMapping("/save")
    public ResponseEntity<?> save(HttpSession session) {
        BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
        if (bankServiceList == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Данные не загружены на сайт");
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bankServiceList.writeInFile3(outputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", "test.json");
        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
}


