package com.practice.practice;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
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
        services_list.add(service);
    }

    public BankService findService(UUID uuid) {
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
}
