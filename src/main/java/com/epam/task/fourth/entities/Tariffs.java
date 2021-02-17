package com.epam.task.fourth.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"tariff"})
@XmlRootElement(name="tariffs")
public class Tariffs {
    protected List<JAXBElement<? extends Tariff>> tariff;

    public List<JAXBElement<? extends Tariff>> getTariff() {
        if(tariff==null){
            tariff = new ArrayList<JAXBElement<? extends Tariff>>();
        }
        return this.tariff;
    }

    @Override
    public String toString() {
        for (JAXBElement<? extends Tariff> jaxbElement : tariff) {
            System.out.println(jaxbElement.getDeclaredType());
        }
        return "Tariffs{" +
                "tariff=" + tariff +
                '}';
    }
}
