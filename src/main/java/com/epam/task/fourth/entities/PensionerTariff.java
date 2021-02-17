package com.epam.task.fourth.entities;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PensionerTariff", propOrder = {"discountPercent"})
public class PensionerTariff extends Tariff{
    @XmlElement(name = "discount-percent")
    private int discountPercent;

    public PensionerTariff(String name, int payroll, CallPrices callPrices, int smsPrice,  Parameters parameters,Operator operator, int discountPercent) {
        super(name, payroll, callPrices, smsPrice, parameters, operator);
        this.discountPercent = discountPercent;
    }

    public PensionerTariff() {
        super();
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    @Override
    public String toString() {
        return "PensionerTariff{" +
                "name='" + this.getName() + '\'' +
                ", payroll=" + this.getPayroll() +
                ", " + this.getCallPrices().toString() +
                ", smsPrice=" + this.getSmsPrice() +
                ", " + this.getParameters().toString() +
                ", operator=" + this.getOperator().toString() +
                "discountPercent=" + discountPercent +
                '}' + "\n";
    }
}
