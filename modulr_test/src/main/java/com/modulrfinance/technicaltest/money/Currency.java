package com.modulrfinance.technicaltest.money;

public enum Currency {
    EURO("£"),
    DOLLAR("$");

    private String value;

    Currency(String value){
        this.value = value;
    }
    public String value(){
        return this.value;
    }
}
