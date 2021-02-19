package com.epam.task.fourth.entities;

import com.epam.task.fourth.adapters.EnumAdapter;
import com.epam.task.fourth.enums.Tariffication;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parameters", propOrder = {"numberOfFavourites", "payment", "tariffication"})
public class Parameters {
    @XmlElement(required = true, name = "number-of-favourites")
    private int numberOfFavourites;
    @XmlElement(required = true)
    private int payment;
    @XmlJavaTypeAdapter(EnumAdapter.class)
    @XmlAttribute(name = "tariffication")
    private Tariffication tariffication = Tariffication.TWELVESECONDS;

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
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        Parameters that = (Parameters) object;
        return numberOfFavourites == that.numberOfFavourites && payment == that.payment && tariffication == that.tariffication;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfFavourites, payment, tariffication);
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
