package com.epam.task.fourth.adapters;

import com.epam.task.fourth.entities.Login;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class IDAdapter extends XmlAdapter<String, Login> {
    @Override
    public Login unmarshal(String string) {
        return new Login(string);
    }

    @Override
    public String marshal(Login login) {
        return login.getLogin();
    }
}
