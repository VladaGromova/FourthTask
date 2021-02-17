package com.epam.task.fourth.entities;

import com.epam.task.fourth.parsers.Parser;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parameters", propOrder = {"numberOfFavourites", "payment", "tariffication"})
public class Parameters {
    @XmlElement(required = true)
    private int numberOfFavourites;
    @XmlElement(required = true)
    private int payment;
    @XmlAttribute
    private Tariffication tariffication=Tariffication.TWELVESECONDS;

    public Parameters(int numberOfFavourites, int payment, Tariffication tariffication) {
        this.numberOfFavourites = numberOfFavourites;
        this.payment = payment;
        this.tariffication = tariffication;
    }

    public Parameters(int numberOfFavourites, int payment) {
        this.numberOfFavourites = numberOfFavourites;
        this.payment = payment;
    }

    public Parameters(){}

    public void setNumberOfFavourites(int numberOfFavourites) {
        this.numberOfFavourites = numberOfFavourites;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public void setTariffication(Tariffication tariffication) {
        this.tariffication = tariffication;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "numberOfFavourites=" + numberOfFavourites +
                ", payment=" + payment +
                ", tariffication=" + tariffication +
                '}';
    }
}
