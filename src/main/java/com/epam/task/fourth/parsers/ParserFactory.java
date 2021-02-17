package com.epam.task.fourth.parsers;

public class ParserFactory {
    public static Parser create(String type) throws ParserException {
        ParserType parserType = ParserType.valueOf(type.toUpperCase());
        switch (parserType){
            case DOM:
                return new DomParser();
            case SAX:
                return new SaxParser();
            case JAXB:
                return new JaxbParser();
            default:
                throw new ParserException("parserType.getDeclaringClass(), parserType.name()");
        }
    }
}
