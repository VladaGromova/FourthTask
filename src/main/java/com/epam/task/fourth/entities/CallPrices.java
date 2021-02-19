package com.epam.task.fourth.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallPrices")
public class CallPrices {
    @XmlElement(required = true, name = "in-network")
    private int inNetwork;
    @XmlElement(required = true, name = "out-network")
    private int outNetwork;
    @XmlElement(required = true)
    private int stationary;

    public CallPrices(int inNetwork, int outNetwork, int stationary) {
        this.inNetwork = inNetwork;
        this.outNetwork = outNetwork;
        this.stationary = stationary;
    }

    public CallPrices() {
    }

    public void setInNetwork(int inNetwork) {
        this.inNetwork = inNetwork;
    }

    public void setOutNetwork(int outNetwork) {
        this.outNetwork = outNetwork;
    }

    public void setStationary(int stationary) {
        this.stationary = stationary;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        CallPrices that = (CallPrices) object;
        return inNetwork == that.inNetwork && outNetwork == that.outNetwork && stationary == that.stationary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inNetwork, outNetwork, stationary);
    }

    @Override
    public String toString() {
        return "CallPrices{" +
                "inNetwork=" + inNetwork +
                ", outNetwork=" + outNetwork +
                ", stationary=" + stationary +
                '}';
    }
}
