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
    public String upload(Model model, HttpSession session, @RequestParam("jsonFile") MultipartFile jsonFile) {
        if (!jsonFile.isEmpty()) {
            try {
                byte[] bytes = jsonFile.getBytes();
                session.setAttribute("uploadedJsonFile", jsonFile);
                bankServiceList = new BankServiceList();
                bankServiceList.readFromFile2(jsonFile);
                session.setAttribute("bankServiceList", bankServiceList);
                String encodedMessage = URLEncoder.encode("Файл успешно загружен", StandardCharsets.UTF_8);
                String redirectUrl = "/table?message=" + encodedMessage + "&type=success";
                return "redirect:" + redirectUrl;
            } catch (IOException e) {
                e.printStackTrace();
                model.addAttribute("message", "Ошибка при загрузке файла.");
                model.addAttribute("messageType", "error");
            }
        } else {
            model.addAttribute("message", "Файл не был выбран.");
            model.addAttribute("messageType", "error");
        }
        return "redirect:/table";
    }
}
