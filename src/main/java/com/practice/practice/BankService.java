package com.practice.practice;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;
@Schema(description = "Сущность банковской услуги")
public class BankService {
    @Schema(description = "Номер контракта", example = "2564")
    private int contract_num;
    @Schema(description = "Название услуги", example = "Кредит")
    private String service_name;
    @Schema(description = "Дата оказания", example = "2023-04-04")
    private String date;
    @Schema(description = "Тип", example = "Потребительский")
    private String type;
    @Schema(description = "Комиссия", example = "150.2")
    private double comission_result;
    @Schema(description = "Долг", example = "50")
    private int debt;
    @Schema(description = "Статус", example = "В работе")
    private String status;
    @Schema(description = "Клиент", example = "Антон В.")
    private String client;
    @Schema(description = "Уникальный идентификатор", example = "407d5c4c-de54-4128-9856-5a8595aa5f7e")
    private UUID uuid;

    public BankService() {
    }
    public BankService(int num, String name, String date, String type, double comission_result, int debt){
        this.uuid = UUID.randomUUID();
        this.contract_num = num;
        this.service_name = name;
        this.date = date;
        this.type = type;
        this.comission_result = comission_result;
        this.debt = debt;
    }

    public UUID getUuid() {return uuid;}

    public void setUuid() {
        this.uuid = UUID.randomUUID();
    }

    public int getContract_num(){return contract_num;}
    public void setContract_num(int num){this.contract_num = num;}

    public String getService_name(){return service_name;}
    public void setService_name(String name){this.service_name = name;}

    public String getDate(){return date;}
    public void setDate(String date){this.date = date;}

    public String getType(){return type;}
    public void setType(String type){this.type = type;}

    public double getComission_result(){return comission_result;}
    public void setComission_result(double comission_result){this.comission_result = comission_result;}

    public int getDebt(){return debt;}
    public void setDebt(int debt){this.debt = debt;}

    public String getStatus(){return status;}
    public void setStatus(String status){this.status = status;}
    public String getClient(){return client;}
    public void setClient(String client){this.client = client;}

    public String toString(){
        return "Bank {" + "UUID = '" + uuid + '\''
                + " Номер договора = '" + contract_num + '\''
                + ", наименование услуги = " + service_name
                + ", срок оказания = " + date + ", тип = " + type + ", результат комиссии = " + comission_result + ", долг = " + debt + ", Статус услуги= " + status
                + "}\n";
    }
}
