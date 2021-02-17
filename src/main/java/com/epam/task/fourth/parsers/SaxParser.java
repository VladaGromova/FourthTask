package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.Tariff;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {

    private final static String EXCEPTION_MESSAGE= "PARSING EXCEPTION";
    private List<Tariff> tariffs;
    private final TariffsHandler handler;
    private XMLReader reader;

    public SaxParser() {
        handler = new TariffsHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public List<Tariff> parse(String filename) throws ParserException {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            throw new ParserException(EXCEPTION_MESSAGE, e);
        }
        tariffs = handler.getTariffs();
        return tariffs;
    }
}
