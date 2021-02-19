package com.epam.task.fourth.parsers;

import org.junit.BeforeClass;

public class DomParserTest extends ParserTest {
    private final static DomParser parser = new DomParser();

    @BeforeClass
    public static void before() {
        director.setParser(parser);
    }
}
