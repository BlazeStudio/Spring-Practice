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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
public class UploadController {

    private BankServiceList bankServiceList;

//    @PostMapping("/upload")
//    public String upload(HttpSession session, @RequestParam("jsonFile") MultipartFile jsonFile) {
//        if (!jsonFile.isEmpty()) {
//            if (jsonFile.getOriginalFilename().endsWith(".json")) {
//                session.setAttribute("uploadedJsonFile", jsonFile);
//                bankServiceList = new BankServiceList();
//                bankServiceList.readFromFile2(jsonFile);
//                session.setAttribute("bankServiceList", bankServiceList);
//                session.setAttribute("message", "Файл успешно загружен");
//                session.setAttribute("messageType", "success");
//            } else {
//                session.setAttribute("message", "Ошибка при загрузке файла.");
//                session.setAttribute("messageType", "error");
//            }
//        } else {
//            session.setAttribute("message", "Файл не был выбран.");
//            session.setAttribute("messageType", "warning");
//        }
//        return "redirect:/table";
//    }
//}
@PostMapping("/upload")
public ResponseEntity<String> upload(HttpSession session, @RequestParam("jsonFile") MultipartFile jsonFile) {
    if (!jsonFile.isEmpty()) {
        if (jsonFile.getOriginalFilename().endsWith(".json")) {
            try {
                byte[] bytes = jsonFile.getBytes();
                session.setAttribute("uploadedJsonFile", jsonFile);
                bankServiceList = new BankServiceList();
                bankServiceList.readFromFile2(jsonFile);
                session.setAttribute("bankServiceList", bankServiceList);
                session.setAttribute("message", "Файл успешно загружен");
                session.setAttribute("messageType", "success");
                return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, "/table").body(null);
            } catch (IOException e) {
                e.printStackTrace();
                session.setAttribute("message", "Ошибка при загрузке файла.");
                session.setAttribute("messageType", "error");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при загрузке файла.");
            }
        } else {
            session.setAttribute("message", "Ошибка при загрузке файла.");
            session.setAttribute("messageType", "error");
            return ResponseEntity.badRequest().body("Ошибка при загрузке файла.");
        }
    } else {
        session.setAttribute("message", "Файл не был выбран.");
        session.setAttribute("messageType", "warning");
        return ResponseEntity.badRequest().body("Файл не был выбран.");
    }
}
}