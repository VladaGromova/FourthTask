package com.epam.task.fourth.entities;

public enum Tariffication {
    TWELVESECONDS("12seconds"),
    ONEMINUTE("1minute");

    private String value;
    private Tariffication(String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}
