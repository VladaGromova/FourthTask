package com.epam.task.fourth.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Login", propOrder = {"login"})
public class Login {
    private final static String PATTERN="([a-zA-Z])[a-zA-Z0-9]{7,19}";
    @XmlID
    private String login;

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
}
