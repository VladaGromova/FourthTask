package com.epam.task.fourth.parsers;

import org.junit.BeforeClass;

public class SaxParserTest extends ParserTest{

    private final static SaxParser parser = new SaxParser();

    @BeforeClass
    public static void before() {
        director.setParser(parser);
    }
}
