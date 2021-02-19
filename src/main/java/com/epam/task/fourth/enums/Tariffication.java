package com.epam.task.fourth.enums;

public enum Tariffication {
    TWELVESECONDS("12seconds"),
    ONEMINUTE("1minute");

    private final String value;
    Tariffication(String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}
