package com.epam.task.fourth.parsers;

import com.epam.task.fourth.enums.Operator;
import com.epam.task.fourth.enums.Tariffication;
import com.epam.task.fourth.enums.TariffsEnum;
import com.epam.task.fourth.entities.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class DomParser implements Parser {

    private final static String EXCEPTION_MESSAGE = "Parser Exception";
    private final static String PENSIONER_TARIFF = "pensioner-tariff";
    private final static String STUDENT_TARIFF = "student-tariff";
    private List<Tariff> tariffs;
    private DocumentBuilder documentBuilder;

    private final static Logger LOGGER = Logger.getLogger(DomParser.class);

    public DomParser(){
        this.tariffs = new ArrayList<>();
        try {
            initializeDocumentBuilder();
        } catch (ParserException e) {
            LOGGER.error(EXCEPTION_MESSAGE, e);
        }
    }

    public List<Tariff> parse(String filename) throws ParserException {
        Document document;
        try {
            document = documentBuilder.parse(filename);
            Element root = document.getDocumentElement();
            process(root, PENSIONER_TARIFF);
            process(root, STUDENT_TARIFF);
        } catch (SAXException | IOException e) {
            throw new ParserException(EXCEPTION_MESSAGE, e);
        }
        return tariffs;
    }

    private void initializeDocumentBuilder() throws ParserException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ParserException(EXCEPTION_MESSAGE, e);
        }
    }

    private void process(Element root, String tariffType) {
        NodeList tariffsList = root.getElementsByTagName(tariffType);
        for (int i = 0; i < tariffsList.getLength(); ++i) {
            Element tariffElement = (Element) tariffsList.item(i);
            Tariff tariff = initializeTariffFields(tariffElement, tariffType);
            tariffs.add(tariff);
        }
    }

    private int getInfo(Element tariffElement, TariffsEnum type){
        String name = type.getValue();
        String elementName = getElementTextContent(tariffElement, name);
        return Integer.parseInt(elementName);
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

    private Tariff initializeTariffFields(Element tariffElement, String tariffType) {

        Tariff tariff = create(tariffType);

        String attributeName = TariffsEnum.OPERATORNAME.getValue();
        String operator = tariffElement.getAttribute(attributeName);
        tariff.setOperator(Operator.valueOf(operator));

        if (tariffType.equals(STUDENT_TARIFF)) {
            attributeName = TariffsEnum.LOGIN.getValue();
            Login login = new Login(tariffElement.getAttribute(attributeName));
            ((StudentTariff) tariff).setLogin(login);
        }

        String name = TariffsEnum.NAME.getValue();
        String elementName = getElementTextContent(tariffElement, name);
        tariff.setName(elementName);

        int payroll = getInfo(tariffElement, TariffsEnum.PAYROLL);
        tariff.setPayroll(payroll);

        int smsPrice = getInfo(tariffElement, TariffsEnum.SMSPRICE);
        tariff.setSmsPrice(smsPrice);
        if (tariffType.equals(PENSIONER_TARIFF)) {
            int discount = getInfo(tariffElement, TariffsEnum.DISCOUNTPERCENT);
            ((PensionerTariff) tariff).setDiscountPercent(discount);
        }
        initializeCallPrices(tariff, tariffElement);
        initializeParameters(tariff, tariffElement);
        return tariff;
    }

    private void initializeParameters(Tariff tariff, Element tariffElement){
        Element parametersElement = (Element) tariffElement.getElementsByTagName(TariffsEnum.PARAMETERS.getValue()).item(0);
        int numberOfFavourites = getInfo(parametersElement, TariffsEnum.NUMBEROFFAVOURITES);
        int payment = getInfo(parametersElement, TariffsEnum.PAYMENT);
        Parameters parameters = new Parameters(numberOfFavourites, payment);

        tariff.setParameters(parameters);

        String attributeName = TariffsEnum.TARIFFICATION.getValue();
        String tariffication = parametersElement.getAttribute(attributeName);
        if(tariffication.equals(Tariffication.ONEMINUTE.getValue())){
            tariff.getParameters().setTariffication(Tariffication.ONEMINUTE);
        }
    }

    private void initializeCallPrices(Tariff tariff, Element tariffElement){
        Element callPricesElement = (Element) tariffElement.getElementsByTagName(TariffsEnum.CALLPRICES.getValue()).item(0);
        int inNetwork = getInfo(callPricesElement, TariffsEnum.INNETWORK);
        int outNetwork = getInfo(callPricesElement, TariffsEnum.OUTNETWORK);
        int stationary = getInfo(callPricesElement, TariffsEnum.STATIONARY);
        CallPrices callPrices = new CallPrices(inNetwork, outNetwork, stationary);
        tariff.setCallPrices(callPrices);
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}
