package com.epam.task.fourth.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"tariff"})
@XmlRootElement(name="tariffs", namespace = "http://www.example.com/tariffs")
public class Tariffs {
    @XmlElementRef(name = "tariff", namespace = "http://www.example.com/tariffs", type = JAXBElement.class)
    protected List<JAXBElement<? extends Tariff>> tariff;

    public Tariffs(){
        super();
    }

    public List<JAXBElement<? extends Tariff>> getTariff() {
        if(tariff==null){
            tariff = new ArrayList<>();
        }
        return tariff;
    }

    public List<Tariff> getTariffsAsList(){
        List<Tariff> result = new ArrayList<>();
        for (JAXBElement<? extends Tariff> jaxbElement : tariff) {
            result.add(jaxbElement.getValue());
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (JAXBElement<? extends Tariff> jaxbElement : tariff) {
            result += jaxbElement.getValue();
        }
        return result;
    }
}
