package com.epam.task.fourth;

import com.epam.task.fourth.entities.Tariff;
import com.epam.task.fourth.parsers.Parser;
import com.epam.task.fourth.parsers.ParserException;
import com.epam.task.fourth.validator.XmlValidator;

import java.util.List;

public class Director {
    private XmlValidator validator;
    private Parser parser;
    private final static String EXCEPTION_MESSAGE = "Parsing Exception";

    public Director(XmlValidator validator, Parser parser) {
        this.validator = validator;
        this.parser = parser;
    }

    public Director(XmlValidator validator) {
        this.validator = validator;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public List<Tariff> parse(String filename) throws ParserException {
        if(validator.isValid(filename)){
            return parser.parse(filename);
        }
        throw new ParserException(EXCEPTION_MESSAGE);
    }
}
