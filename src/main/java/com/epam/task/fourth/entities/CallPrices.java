package com.epam.task.fourth.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallPrices", propOrder = {"inNetwork", "outNetwork", "stationary"})
public class CallPrices {
    @XmlElement(required = true)
    private int inNetwork;
    @XmlElement(required = true)
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
    public String toString() {
        return "CallPrices{" +
                "inNetwork=" + inNetwork +
                ", outNetwork=" + outNetwork +
                ", stationary=" + stationary +
                '}';
    }
}
