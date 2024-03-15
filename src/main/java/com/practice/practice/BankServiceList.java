package com.practice.practice;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class BankServiceList {
    private List<BankService> services_list = new ArrayList<>();

    public List<BankService> getServices_list() {
        return services_list;
    }

    public void setServicesList(List<BankService> services_list) {
        this.services_list = services_list;
    }
    public void addService(BankService service) {
        service.setUuid();
        services_list.add(service);
    }

    public BankService findService(UUID uuid) {
        System.out.println(services_list);
        for (BankService service : services_list) {
            if (service.getUuid().equals(uuid)) {
                return service;
            }
        }
        return null;
    }
    public void deleteService(UUID uuid) {
        BankService foundService = findService(uuid);
        if (foundService != null) {
            services_list.remove(foundService);
            System.out.println("Объект с UUID " + uuid + " удален из списка.");
        } else {
            System.out.println("Объект с UUID " + uuid + " не найден в списке.");
        }
    }
    public void showService(UUID uuid) {
        BankService foundService = findService(uuid);
        if (foundService != null) {
            System.out.println(foundService);
        } else {
            System.out.println("Объект с UUID " + uuid + " не найден в списке.");
        }
    }

    public void clear() {
        services_list.clear();
    }

    public void writeInFile(String filename){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(filename), services_list);
            System.out.println("Данные успешно записаны в файл " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeInFile2(OutputStream outputStream){
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(outputStream, services_list);
            System.out.println("Данные успешно записаны в файл");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeInFile3(OutputStream outputStream){
        try {

            if (services_list == null) {
                System.out.println("Список банковских услуг пуст.");
                return;
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            byte[] jsonData = mapper.writeValueAsBytes(services_list);
            outputStream.write(jsonData);
            System.out.println("Данные успешно записаны в файл");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readFromFile(String filename) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            List<BankService> servicesFromFile = mapper.readValue(new File(filename), new TypeReference<List<BankService>>(){});
            services_list.addAll(servicesFromFile);
            System.out.println("Данные успешно прочитаны из файла " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile2(MultipartFile file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            InputStream inputStream = file.getInputStream();

            List<BankService> servicesFromFile = mapper.readValue(inputStream, new TypeReference<List<BankService>>(){});

            // Добавляем прочитанные данные в ваш список или коллекцию
            services_list.addAll(servicesFromFile);

            System.out.println("Данные успешно прочитаны из файла");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
