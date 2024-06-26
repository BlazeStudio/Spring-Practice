package com.practice.practice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.ui.Model;
import com.practice.practice.BankService;
import com.practice.practice.BankServiceList;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="AddController", description="Контроллер добавления новой услуги")
@RestController
public class AddController {
    @Operation(summary = "Добавляет новую услугу",
            description= "UUID и contract_num присваиваются автоматически.")
    @PostMapping("/add-service")
    public ResponseEntity<?> addService(@ModelAttribute BankService service,  HttpSession session) {
        BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
        int contract_num = bankServiceList.findMaxContractNum();
        service.setContract_num(contract_num + 1);
        bankServiceList.addService(service);
        return ResponseEntity.ok(bankServiceList.getServices_list());
    }
}
