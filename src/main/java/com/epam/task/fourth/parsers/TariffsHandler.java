package com.epam.task.fourth.parsers;

import com.epam.task.fourth.enums.Operator;
import com.epam.task.fourth.enums.Tariffication;
import com.epam.task.fourth.enums.TariffsEnum;
import com.epam.task.fourth.entities.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TariffsHandler extends DefaultHandler {

    private final static String REGEX = "[\\-\\s]";
    private final static String PENSIONER_TARIFF = "pensioner-tariff";
    private final static String STUDENT_TARIFF = "student-tariff";
    private final static String START_MESSAGE = "Parsing started.";
    private final static String FINISH_MESSAGE = "Parsing finished.";
    private final static String PARAMETERS = "parameters";
    private final static String DASH = "-";
    private static final String EMPTY_STRING = "";
    private List<Tariff> tariffs;
    private Tariff currentTariff;
    private TariffsEnum currentEnum;

    private final static Logger LOGGER = Logger.getLogger(TariffsHandler.class);

    public TariffsHandler() {
        tariffs = new ArrayList<>();
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startDocument() {
        LOGGER.info(START_MESSAGE);
    }

    @Override
    public void endDocument() {
        LOGGER.info(FINISH_MESSAGE);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (STUDENT_TARIFF.equals(localName) || PENSIONER_TARIFF.equals(localName)) {
            initializeTariffFields(localName, attributes);
        }else if(localName.equals(PARAMETERS)){
            if(attributes.getLength()!=0) {
                setTariffication(attributes);
            }
        } else {
            setTariffFields(localName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (PENSIONER_TARIFF.equals(localName) || STUDENT_TARIFF.equals(localName)) {
            tariffs.add(currentTariff);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case NAME:
                    currentTariff.setName(data);
                    break;
                case PAYROLL:
                    int payroll = Integer.parseInt(data);
                    currentTariff.setPayroll(payroll);
                    break;
                case CALLPRICES:
                    currentTariff.createCallPrices();
                    break;
                case INNETWORK:
                    int inNetworkPrice = Integer.parseInt(data);
                    currentTariff.getCallPrices().setInNetwork(inNetworkPrice);
                    break;
                case OUTNETWORK:
                    int outNetworkPrice = Integer.parseInt(data);
                    currentTariff.getCallPrices().setOutNetwork(outNetworkPrice);
                    break;
                case STATIONARY:
                    int stationaryPrice = Integer.parseInt(data);
                    currentTariff.getCallPrices().setStationary(stationaryPrice);
                    break;
                case SMSPRICE:
                    int smsPrice = Integer.parseInt(data);
                    currentTariff.setSmsPrice(smsPrice);
                    break;
                case NUMBEROFFAVOURITES:
                    int numberOfFavourites = Integer.parseInt(data);
                    currentTariff.getParameters().setNumberOfFavourites(numberOfFavourites);
                    break;
                case PARAMETERS:
                    currentTariff.createParameter();
                    break;
                case PAYMENT:
                    int payment = Integer.parseInt(data);
                    currentTariff.getParameters().setPayment(payment);
                    break;
                case DISCOUNTPERCENT:
                    int discount = Integer.parseInt(data);
                    ((PensionerTariff) currentTariff).setDiscountPercent(discount);
                    break;
                default:
                    break;
            }
        }
        currentEnum = null;
    }

    private void setTariffFields(String localName) {
        String name = localName;
        if (localName.contains(DASH)) {
            name = editString(localName);
        }
        currentEnum = TariffsEnum.valueOf(name.toUpperCase());
    }


    private Tariff create(String type){
        switch (type){
            case STUDENT_TARIFF:
                return new StudentTariff();
            case PENSIONER_TARIFF:
                return new PensionerTariff();
            default:
                return new Tariff();
        }
    }

    private void initializeTariffFields(String localName, Attributes attributes) {
        currentTariff = create(localName);
        String operator = attributes.getValue(0);
        currentTariff.setOperator(Operator.valueOf(operator));
        if (attributes.getLength() == 2) {
            ((StudentTariff) currentTariff).createLogin();
            String data = attributes.getValue(1);
            Login login = ((StudentTariff) currentTariff).getLogin();
            login.setLogin(data);
        }
    }

    private String editString(String string) {
        string = string.replaceAll(REGEX, EMPTY_STRING);
        return string;
    }

    private void setTariffication(Attributes attributes) {
        String tariffication = attributes.getValue(0);
        if (tariffication.equals(Tariffication.ONEMINUTE.getValue())) {
            currentTariff.getParameters().setTariffication(Tariffication.ONEMINUTE);
        }
    }

}
