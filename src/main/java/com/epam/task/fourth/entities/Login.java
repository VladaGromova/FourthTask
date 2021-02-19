package com.epam.task.fourth.entities;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Login")
public class Login {

    @XmlAttribute(name = "login")
    private String login="";

    public Login() {}

    public Login(String login) {
        this.login = login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        Login that = (Login) object;
        return that.login.equals(login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
