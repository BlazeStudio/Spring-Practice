package com.practice.practice.controllers;

import com.practice.practice.BankServiceList;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class UploadController {

    private BankServiceList bankServiceList;

    @PostMapping("/upload")
    public String upload(HttpSession session, @RequestParam("jsonFile") MultipartFile jsonFile) {
        if (!jsonFile.isEmpty()) {
            if (jsonFile.getOriginalFilename().endsWith(".json")) {
            try {
                byte[] bytes = jsonFile.getBytes();
                session.setAttribute("uploadedJsonFile", jsonFile);
                bankServiceList = new BankServiceList();
                bankServiceList.readFromFile2(jsonFile);
                session.setAttribute("bankServiceList", bankServiceList);
                session.setAttribute("message", "Файл успешно заргужен");
                session.setAttribute("messageType", "success");
            } catch (IOException e) {
                e.printStackTrace();
                session.setAttribute("message", "Ошибка при загрузке файла.");
                session.setAttribute("messageType", "error");
            }
            } else {
                session.setAttribute("message", "Ошибка при загрузке файла.");
                session.setAttribute("messageType", "error");
            }
        } else {
            session.setAttribute("message", "Файл не был выбран.");
            session.setAttribute("messageType", "warning");
        }
        return "redirect:/table";
    }
}
