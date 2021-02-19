package com.epam.task.fourth.entities;

import com.epam.task.fourth.enums.Operator;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PensionerTariff")
public class PensionerTariff extends Tariff{
    @XmlElement(name = "discount-percent")
    private int discountPercent;

    public PensionerTariff(String name, int payroll, CallPrices callPrices, int smsPrice, Parameters parameters, Operator operator, int discountPercent) {
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
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        if (!super.equals(object)) {
            return false;
        }
        PensionerTariff that = (PensionerTariff) object;
        return discountPercent == that.discountPercent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discountPercent);
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
                ", discountPercent=" + discountPercent +
                '}' + "\n";
    }
}
