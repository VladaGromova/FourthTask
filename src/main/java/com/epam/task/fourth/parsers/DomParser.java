package com.epam.task.fourth.parsers;

import com.epam.task.fourth.TariffsEnum;
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

public class DomParser implements Parser {

    private final static String EXCEPTION_MESSAGE = "Parser Exception";
    private final static String PENSIONER_TARIFF = "pensioner-tariff";
    private final static String STUDENT_TARIFF = "student-tariff";
    private List<Tariff> tariffs;
    private final DocumentBuilder documentBuilder;

    public DomParser() throws ParserException {
        this.tariffs = new ArrayList<>();
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
            Tariff tariff = createTariff(tariffElement, tariffType);
            tariffs.add(tariff);
        }
    }

    private int getInfo(Element tariffElement, TariffsEnum TYPE){
        String name = TYPE.getValue();
        String elementName = getElementTextContent(tariffElement, name);
        return Integer.parseInt(elementName);
    }

    private Tariff createTariff(Element tariffElement, String tariffType) {

        Tariff tariff = TariffFactory.create(tariffType);

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

        Element callPricesElement = (Element) tariffElement.getElementsByTagName(TariffsEnum.CALLPRICES.getValue()).item(0);

        int inNetwork = getInfo(callPricesElement, TariffsEnum.INNETWORK);
        int outNetwork = getInfo(callPricesElement, TariffsEnum.OUTNETWORK);
        int stationary = getInfo(callPricesElement, TariffsEnum.STATIONARY);
        CallPrices callPrices = new CallPrices(inNetwork, outNetwork, stationary);
        tariff.setCallPrices(callPrices);

        Element parametersElement = (Element) tariffElement.getElementsByTagName(TariffsEnum.PARAMETERS.getValue()).item(0);
        int numberOfFavourites = getInfo(parametersElement, TariffsEnum.NUMBEROFFAVOURITES);
        int payment = getInfo(parametersElement, TariffsEnum.PAYMENT);
        Parameters parameters = new Parameters(numberOfFavourites, payment);

        tariff.setParameters(parameters);

        return tariff;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        return node.getTextContent();
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
}
