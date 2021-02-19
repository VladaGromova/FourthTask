package com.epam.task.fourth.entities;

import com.epam.task.fourth.enums.Tariffication;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private final static String NAMESPACE = "http://www.example.com/tariffs";
    private final static String TARIFF = "tariff";
    private final static String STUDENT_TARIFF = "student-tariff";
    private final static String PENSIONER_TARIFF = "pensioner-tariff";
    private final static QName _Tariff_QNAME = new QName(NAMESPACE, TARIFF);
    private final static QName _StudentTariff_QNAME = new QName(NAMESPACE, STUDENT_TARIFF);
    private final static QName _PensionerTariff_QNAME = new QName(NAMESPACE, PENSIONER_TARIFF);

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
    public Tariffication createTariffication(){return Tariffication.TWELVESECONDS;}
    public Login createLogin(){
        return new Login();
    }
    @XmlElementDecl(namespace = NAMESPACE, name = TARIFF)
    public JAXBElement<Tariff> createTariff(Tariff value){
        return new JAXBElement<Tariff>(_Tariff_QNAME, Tariff.class, null, value);
    }
    @XmlElementDecl(namespace = NAMESPACE, name = STUDENT_TARIFF, substitutionHeadNamespace = NAMESPACE, substitutionHeadName = TARIFF)
    public JAXBElement<StudentTariff> createStudentTariff(StudentTariff value){
        return new JAXBElement<StudentTariff>(_StudentTariff_QNAME, StudentTariff.class, null, value);
    }
    @XmlElementDecl(namespace = NAMESPACE, name = PENSIONER_TARIFF, substitutionHeadNamespace = NAMESPACE, substitutionHeadName = TARIFF)
    public JAXBElement<PensionerTariff> createPensionerTariff(PensionerTariff value){
        return new JAXBElement<PensionerTariff>(_PensionerTariff_QNAME, PensionerTariff.class, null, value);
    }

}

