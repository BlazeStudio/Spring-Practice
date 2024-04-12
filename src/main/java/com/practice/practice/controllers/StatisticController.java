package com.practice.practice.controllers;

import com.practice.practice.BankService;
import com.practice.practice.BankServiceList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name="StatisticController", description="Контроллер для подсчёта статистики")
@RestController
public class StatisticController {
    @Operation(summary = "Производит статистические вычисления",
            description= "Производит ряд вычислений с результатом комиссии и долгом")
    @GetMapping("/statistics")
    public ResponseEntity<String> calculateStatistics(HttpSession session, @RequestHeader(value = "test_header", required = false) String test_header) {
        if (test_header == null || test_header.isEmpty()) {
            session.setAttribute("message", "Отсутствует необходимый заголовок");
            session.setAttribute("messageType", "error");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Отсутствует необходимый заголовок");
        }
        BankServiceList bankServiceList = (BankServiceList) session.getAttribute("bankServiceList");
        if (bankServiceList == null || bankServiceList.isEmpty()) {
            session.setAttribute("message", "404 - Список услуг пуст");
            session.setAttribute("messageType", "error");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404 - Список услуг пуст");
        }

        List<Double> commissionResults = new ArrayList<>();
        List<Integer> debts = new ArrayList<>();
        StringBuilder responseMessage = new StringBuilder();

        for (BankService bankService : bankServiceList.getServices()) {
            double commission = bankService.getComission_result();
            commissionResults.add(commission);
            debts.add(bankService.getDebt());
            responseMessage.append("Услуга: ").append(bankService.getService_name()).append(", Коммиссия: ").append(commission).append(", Долг: ").append(bankService.getDebt()).append("\n");
        }

        double sum = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (double result : commissionResults) {
            sum += result;
            if (result < min) {
                min = result;
            }
            if (result > max) {
                max = result;
            }
        }

        double mean = sum / commissionResults.size();

        double varianceSum = 0;
        for (double result : commissionResults) {
            varianceSum += Math.pow(result - mean, 2);
        }
        double variance = varianceSum / commissionResults.size();

        double arithmeticMean = sum / commissionResults.size();

        double weightedSum = 0;
        int totalDebt = 0;
        for (int i = 0; i < commissionResults.size(); i++) {
            weightedSum += commissionResults.get(i) * debts.get(i);
            totalDebt += debts.get(i);
        }
        double weightedMean = weightedSum / totalDebt;

        responseMessage.append("\n\nСтатистика:\n");
        responseMessage.append("Формула для минимума: min(commissionResults)\n");
        responseMessage.append("Минимум: ").append(min).append("\n");
        responseMessage.append("Формула для максимума: max(commissionResults)\n");
        responseMessage.append("Максимум: ").append(max).append("\n");
        responseMessage.append("Формула математического ожидания: sum(commissionResults) / commissionResults.size()\n");
        responseMessage.append("Математическое ожидание: ").append(arithmeticMean).append("\n");
        responseMessage.append("Формула для среднего взвешенного: sum(commissionResults * debts) / sum(debts)\n");
        responseMessage.append("Среднее взвешенное: ").append(weightedMean);
        responseMessage.append("\nФормула для дисперсии: sum((x - mean)^2) / commissionResults.size()\n");
        responseMessage.append("Дисперсия: ").append(variance).append("\n");

        session.setAttribute("message", "Статистика успешно рассчитана");
        session.setAttribute("messageType", "success");

        return ResponseEntity.ok(responseMessage.toString());
    }
}
