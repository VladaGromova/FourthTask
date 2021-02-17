package com.epam.task.fourth.entities;

public enum Operator {
    MTS("MTS"),
    A("A"),
    LIFE("LIFE");

    private String value;
    private Operator(String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}
