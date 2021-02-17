package com.epam.task.fourth.entities;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentTariff", propOrder = {"login"})
public class StudentTariff extends Tariff{
    @XmlElement(required = true)
    private Login login;

    public StudentTariff(String name, int payroll, CallPrices callPrices, int smsPrice,  Parameters parameters, Operator operator, Login login) {
        super(name, payroll, callPrices, smsPrice, parameters, operator);
        this.login = login;
    }

    public StudentTariff(){}

    public void setLogin(Login login) {
        this.login = login;
    }

    public Login getLogin() {
        return login;
    }

    public void createLogin(){login = new Login();}

    @Override
    public String toString() {
        return "StudentTariff{" +
                ", login:" + this.getLogin().getLogin() +
                "name='" + this.getName() + '\'' +
                ", payroll=" + this.getPayroll() +
                ", " + this.getCallPrices().toString() +
                ", smsPrice=" + this.getSmsPrice() +
                ", " + this.getParameters().toString() +
                ", operator=" + this.getOperator().toString() +
                '}' +'\n';
    }
}
