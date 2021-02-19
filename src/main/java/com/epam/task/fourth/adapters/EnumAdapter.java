package com.epam.task.fourth.adapters;

import com.epam.task.fourth.enums.Tariffication;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EnumAdapter extends XmlAdapter<String, Tariffication> {
    @Override
    public Tariffication unmarshal(String tariffication) {
        if(tariffication.equals(Tariffication.ONEMINUTE.getValue())){
            return Tariffication.ONEMINUTE;
        }
        return Tariffication.TWELVESECONDS;
    }

    @Override
    public String marshal(Tariffication tariffication) {
        return tariffication.getValue();
    }
}
