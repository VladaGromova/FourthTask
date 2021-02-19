package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.Tariff;
import com.epam.task.fourth.entities.Tariffs;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JaxbParser implements Parser {

    private final static String TARIFFS_XML = "src/main/resources/tariffs.xml";
    private final static String EXCEPTION_MESSAGE = "Jaxb parsing exception";

    public List<Tariff> parse(String file) throws ParserException {
        JAXBContext jaxbContext;
        Tariffs tariffs;
        try {
            jaxbContext = JAXBContext.newInstance(Tariffs.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            tariffs = (Tariffs) unmarshaller.unmarshal(new File(TARIFFS_XML));
        } catch (JAXBException e) {
            throw new ParserException(EXCEPTION_MESSAGE, e);
        }
        return tariffs.getTariffsAsList();
    }
}
