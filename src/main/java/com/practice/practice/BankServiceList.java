package com.practice.practice;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

    public int findMaxContractNum() {
        if (services_list.isEmpty()) {
            System.out.println("Список банковских услуг пуст.");
            return 0;
        }

        int maxContractNum = Integer.MIN_VALUE;
        for (BankService service : services_list) {
            int contractNum = service.getContract_num();
            if (contractNum > maxContractNum) {
                maxContractNum = contractNum;
            }
        }
        return maxContractNum;
    }
    public void setServicesList(List<BankService> services_list) {
        this.services_list = services_list;
    }
    public void addService(BankService service) {
        service.setUuid();
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
    public ResponseEntity<ResponseEntity<String>> deleteService(String uuidString) {
        try {
            UUID uuid = UUID.fromString(uuidString);
            BankService foundService = findService(uuid);
            if (foundService != null) {
                services_list.remove(foundService);
                return ResponseEntity.ok().body(ResponseEntity.ok("Сервис успешно удален"));
            } else {
                return ResponseEntity.ok().body(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Сервис не найден"));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.ok().body(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверный формат UUID"));
        }
    }

    public int size() {
        return services_list.size();
    }
    public ResponseEntity<BankService> showService(UUID uuid) {
        try {
            BankService foundService = findService(uuid);
            if (foundService != null) {
                return ResponseEntity.ok(foundService);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    public void clear() {
        services_list.clear();
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

    public void readFromFile2(MultipartFile file) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);

            InputStream inputStream = file.getInputStream();

            List<BankService> servicesFromFile = mapper.readValue(inputStream, new TypeReference<List<BankService>>(){});

            services_list.addAll(servicesFromFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean isEmpty() {
        return services_list.isEmpty();
    }

    public List<BankService> getServices() {
        return services_list;
    }
}
