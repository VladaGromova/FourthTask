package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.Tariff;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {

    private final static String EXCEPTION_MESSAGE= "PARSING EXCEPTION";
    private final static String SUCCESS_PARSE_INFO = "XML is parsed using SAX parser";
    private final static Logger LOGGER = Logger.getLogger(SaxParser.class);

    public SaxParser(){
    }

    public List<Tariff> parse(String filename) throws ParserException {
        TariffsHandler handler = new TariffsHandler();
        XMLReader reader;
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            throw new ParserException(EXCEPTION_MESSAGE, e);
        }
        LOGGER.info(SUCCESS_PARSE_INFO);
        return handler.getTariffs();
    }

}
