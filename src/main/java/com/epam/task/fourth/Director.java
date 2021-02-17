package com.epam.task.fourth;

import com.epam.task.fourth.entities.Tariff;
import com.epam.task.fourth.parsers.Parser;
import com.epam.task.fourth.parsers.ParserException;
import com.epam.task.fourth.parsers.ParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class Director {
    private XmlValidator validator;
    private Parser parser;

    public Director(XmlValidator validator, Parser parser) {
        this.validator = validator;
        this.parser = parser;
    }

    public List<Tariff> parse(String filename) throws ParserException {
        if(validator.isValid(filename)){
            return  parser.parse(filename);
        }
        return null;
    }
}
