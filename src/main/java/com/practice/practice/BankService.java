package com.practice.practice;

import java.util.UUID;

public class BankService {
    private int contract_num;
    private String service_name;
    private int date;
    private String type;
    private double comission_result;
    private int debt;
    private boolean service_status;
    private UUID uuid;

    public BankService() {
    }
    public BankService(int num, String name, int date, String type, double comission_result, int debt){
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

    public int getDate(){return date;}
    public void setDate(int date){this.date = date;}

    public String getType(){return type;}
    public void setType(String type){this.type = type;}

    public double getComission_result(){return comission_result;}
    public void setComission_result(double comission_result){this.comission_result = comission_result;}

    public int getDebt(){return debt;}
    public void setDebt(int debt){this.debt = debt;}

    public String toString(){
        return "Bank {" + "UUID = '" + uuid + '\''
                + " Номер договора = '" + contract_num + '\''
                + ", наименование услуги = " + service_name
                + ", срок оказания = " + date + ", тип = " + type + ", результат комиссии = " + comission_result + ", долг = " + debt + ", Статус услуги= " + service_status
                + "}\n";
    }
}
