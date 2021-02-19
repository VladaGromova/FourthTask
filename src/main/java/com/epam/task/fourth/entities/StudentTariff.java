package com.epam.task.fourth.entities;

import com.epam.task.fourth.adapters.IDAdapter;
import com.epam.task.fourth.enums.Operator;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StudentTariff")
public class StudentTariff extends Tariff{
    @XmlID
    @XmlJavaTypeAdapter(IDAdapter.class)
    @XmlAttribute(required = true, name = "login")
    private Login login = new Login();

    public StudentTariff(String name, int payroll, CallPrices callPrices, int smsPrice, Parameters parameters, Operator operator, Login login) {
        super(name, payroll, callPrices, smsPrice, parameters, operator);
        this.login = login;
    }

    public StudentTariff(){
        super();
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Login getLogin() {
        return login;
    }

    public void createLogin(){login = new Login();}

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        if (!super.equals(object)){
            return false;
        }
        StudentTariff that = (StudentTariff) object;
        return Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), login);
    }

    @Override
    public String toString() {
        return "StudentTariff{" +
                "login:" + this.getLogin().getLogin() +
                ", name='" + this.getName() + '\'' +
                ", payroll=" + this.getPayroll() +
                ", " + this.getCallPrices().toString() +
                ", smsPrice=" + this.getSmsPrice() +
                ", " + this.getParameters().toString() +
                ", operator=" + this.getOperator().toString() +
                '}' +'\n';
    }
}
