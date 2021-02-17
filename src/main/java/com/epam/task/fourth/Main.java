package com.epam.task.fourth;

import com.epam.task.fourth.parsers.*;

public class Main {

    private final static String XML_FILE = "C:\\Java Web Development\\projects\\FourthTask\\FourthTask\\src\\main\\resources\\tariffs.xml";
    private final static String XSD_FILE = "C:\\Java Web Development\\projects\\FourthTask\\FourthTask\\src\\main\\resources\\tariffs.xsd";

    public static void main(String[] args) throws ParserException {
/*
        XmlValidator validator = new XmlValidator(XSD_FILE);
        Parser parser = ParserFactory.create("sax");
        Director director = new Director(validator, parser);
        System.out.println(director.parse(XML_FILE));
*/
        JaxbParser p = new JaxbParser();
        p.jaxbParsing();
    }
}
