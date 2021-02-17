package com.epam.task.fourth.entities;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tariff", propOrder = {"name", "payroll", "callPrices","smsPrice","parameters"})
@XmlSeeAlso({StudentTariff.class, PensionerTariff.class})
public class Tariff {
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private int payroll;
    @XmlElement(required = true)
    private CallPrices callPrices;
    @XmlElement(required = true)
    private int smsPrice;
    @XmlElement(required = true)
    private Parameters parameters;
    @XmlAttribute(required = true)
    private Operator operator;

    public Tariff(String name, int payroll, CallPrices callPrices, int smsPrice, Parameters parameters, Operator operator) {
        this.name = name;
        this.payroll = payroll;
        this.callPrices = callPrices;
        this.smsPrice = smsPrice;
        this.operator = operator;
        this.parameters = parameters;
    }

    public Tariff() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setPayroll(int payroll) {
        this.payroll = payroll;
    }

    public void setCallPrices(CallPrices callPrices) {
        this.callPrices = callPrices;
    }

    public void setSmsPrice(int smsPrice) {
        this.smsPrice = smsPrice;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public void setOperator(Operator operator){
        this.operator = operator;
    }

    public void createCallPrices(){
        callPrices = new CallPrices();
    }

    public void createParameter(){

        if (parameters == null) {
            parameters = new Parameters();
        }
    }

    public CallPrices getCallPrices() {
        return callPrices;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public String getName() {
        return name;
    }

    public int getPayroll() {
        return payroll;
    }

    public int getSmsPrice() {
        return smsPrice;
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                ", payroll=" + payroll +
                ", callPrices=" + callPrices +
                ", smsPrice=" + smsPrice +
                ", parameters=" + parameters +
                ", operator=" + operator +
                '}';
    }
}
