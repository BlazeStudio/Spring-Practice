package com.practice.practice;

public class Main {
    public static void main(String[] args) {
        BankService service1 = new BankService(1, "Сервис 1", 29022024, "Тип 1", 100.0, 0);
        BankService service2 = new BankService(2, "Сервис 2", 29022024, "Тип 2", 150.0, 50);
//        UUID test = service1.getUuid();
        BankServiceList serviceList = new BankServiceList();
        serviceList.addService(service1);
        serviceList.addService(service2);
        serviceList.writeInFile("bank.json");
        System.out.println(serviceList.getServices_list());
        BankServiceList serviceList2 = new BankServiceList();
        serviceList2.readFromFile("bank.json");
        System.out.println(serviceList2.getServices_list());
    }
}