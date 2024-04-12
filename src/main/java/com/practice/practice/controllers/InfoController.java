package com.practice.practice.controllers;

import com.practice.practice.BankServiceList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Tag(name="InfoController", description="Контроллер для отображения информации о проекте")
@RestController
public class InfoController {
    @Operation(summary = "Выводит всю информацию о проекте",
            description= "Для вывода списка услуг и их колчества требуется загрузить файл в сессию")
    @GetMapping("/info")
    public ResponseEntity<?> getInfo(HttpSession session) {
        BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
        Map<String, Object> information = new LinkedHashMap<>(); // Changed to Object type
        information.put("name", "BankService");
        information.put("version", "1.0");
        information.put("javaVersion", "21");
        information.put("author", "Anton Vasiliev");
        information.put("year", "2024");
        if (bankServiceList != null) {
            information.put("Services", bankServiceList.getServices_list());
            information.put("ServiceCount", bankServiceList.size());
        }
        else {
            information.put("Services", "[]");
            information.put("ServiceCount", "0");
        }
        return ResponseEntity.ok(information);
    }
}

