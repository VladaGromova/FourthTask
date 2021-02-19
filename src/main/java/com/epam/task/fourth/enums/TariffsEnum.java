package com.epam.task.fourth.enums;

public enum TariffsEnum {
    TARIFFS("tariffs"),
    TARIFF("tariff"),
    STUDENTTARIFF("student-tariff"),
    PENSIONERTARIFF("pensioner-tariff"),
    LOGIN("login"),
    TARIFFICATION("tariffication"),
    OPERATORNAME("operator-name"),
    NAME("name"),
    PAYROLL("payroll"),
    CALLPRICES("call-prices"),
    INNETWORK("in-network"),
    OUTNETWORK("out-network"),
    STATIONARY("stationary"),
    SMSPRICE("sms-price"),
    PARAMETERS("parameters"),
    NUMBEROFFAVOURITES("number-of-favourites"),
    PAYMENT("payment"),
    DISCOUNTPERCENT("discount-percent");

    private final String value;
    TariffsEnum(String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}
