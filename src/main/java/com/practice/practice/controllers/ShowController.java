package com.practice.practice.controllers;

import com.practice.practice.BankService;
import com.practice.practice.BankServiceList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Tag(name="ShowController", description="Контроллер просмотра одной услуги")
@RestController
public class ShowController {
    @Operation(summary = "Выводит услугу по UUID",
            description= "Ищет услугу по UUID. При нахождении выводит все атрибуты, в противном случае - выбрасывет exception")
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