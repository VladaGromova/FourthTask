package com.epam.task.fourth.parsers;

import com.epam.task.fourth.TariffsEnum;
import com.epam.task.fourth.entities.Operator;
import com.epam.task.fourth.entities.PensionerTariff;
import com.epam.task.fourth.entities.StudentTariff;
import com.epam.task.fourth.entities.Tariff;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TariffsHandler extends DefaultHandler {

    private final static String REGEX = "[\\-\\s]";
    private final static String PENSIONER_TARIFF = "pensioner-tariff";
    private final static String STUDENT_TARIFF = "student-tariff";
    private final static String EXCEPTION_MESSAGE = "Parser Exception";
    private List<Tariff> tariffs;
    private Tariff currentTariff = null;
    private TariffsEnum currentEnum = null;
    private EnumSet<TariffsEnum> set;

    private final static Logger LOGGER = Logger.getLogger(TariffsHandler.class);

    public TariffsHandler() {
        tariffs = new ArrayList<>();
        set = EnumSet.range(TariffsEnum.NAME, TariffsEnum.DISCOUNTPERCENT);
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    @Override
    public void startDocument() {
        LOGGER.info("Parsing started.");
    }

    @Override
    public void endDocument() {
        LOGGER.info("Parsing ended.");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (STUDENT_TARIFF.equals(localName) || PENSIONER_TARIFF.equals(localName)) {
            createTariff(localName, attributes);
        } else {
            setTariffFields(localName);
        }
    }

    private void setTariffFields(String localName) {
        String name = localName;
        if (localName.contains("-")) {
            name = editString(localName);
        }
        TariffsEnum temp = TariffsEnum.valueOf(name.toUpperCase());
        if (set.contains(temp)) {
            currentEnum = temp;
        }
    }

    private void createTariff(String localName, Attributes attributes) {
        currentTariff = TariffFactory.create(localName);
        String operator = attributes.getValue(0);
        currentTariff.setOperator(Operator.valueOf(operator));
        if (attributes.getLength() == 2) {
            ((StudentTariff) currentTariff).createLogin();
            ((StudentTariff) currentTariff).getLogin().setLogin(attributes.getValue(1));
        }
    }

    private String editString(String string) {
        string = string.replaceAll(REGEX, "");
        return string;
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
                    try {
                        throw new ParserException(EXCEPTION_MESSAGE);
                    } catch (ParserException e) {
                        LOGGER.error(EXCEPTION_MESSAGE, e);
                    }
            }
        }
        currentEnum = null;
    }
}
