package com.epam.task.fourth.entities;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private final static QName _Tariff_QNAME = new QName("http://www.example.com/tariffs", "tariff");
    private final static QName _StudentTariff_QNAME = new QName("http://www.example.com/tariffs", "student-tariff");
    private final static QName _PensionerTariff_QNAME = new QName("http://www.example.com/tariffs", "pensioner-tariff");

    public ObjectFactory(){}
    public Tariffs createTariffs(){
        return new Tariffs();
    }

    public Tariff createTariff(){
        return new Tariff();
    }
    public PensionerTariff createPensionerTariff(){
        return new PensionerTariff();
    }
    public StudentTariff createStudentTariff(){
        return new StudentTariff();
    }
    public Parameters createParameters(){
        return new Parameters();
    }
    public CallPrices createCallPrices(){
        return new CallPrices();
    }
    public Login createLogin(){
        return new Login();
    }
    @XmlElementDecl(namespace = "http://www.example.com/tariffs", name = "tariff")
    public JAXBElement<Tariff> createTariff(Tariff value){
        return new JAXBElement<Tariff>(_Tariff_QNAME, Tariff.class, null, value);
    }
    @XmlElementDecl(namespace = "http://www.example.com/tariffs", name = "student-tariff", substitutionHeadNamespace = "http://www.example.com/tariffs", substitutionHeadName = "tariff")
    public JAXBElement<StudentTariff> createStudentTariff(StudentTariff value){
        return new JAXBElement<StudentTariff>(_StudentTariff_QNAME, StudentTariff.class, null, value);
    }
    @XmlElementDecl(namespace = "http://www.example.com/tariffs", name = "pensioner-tariff", substitutionHeadNamespace = "http://www.example.com/tariffs", substitutionHeadName = "tariff")
    public JAXBElement<PensionerTariff> createPensionerTariff(PensionerTariff value){
        return new JAXBElement<PensionerTariff>(_PensionerTariff_QNAME, PensionerTariff.class, null, value);
    }

}
