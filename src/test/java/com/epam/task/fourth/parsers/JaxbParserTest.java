package com.epam.task.fourth.parsers;

import org.junit.BeforeClass;

public class JaxbParserTest extends ParserTest{
    private final static JaxbParser parser = new JaxbParser();

    @BeforeClass
    public static void before() {
        director.setParser(parser);
    }
}
