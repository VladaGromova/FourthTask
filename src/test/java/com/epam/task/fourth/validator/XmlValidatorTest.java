package com.epam.task.fourth.validator;

import com.epam.task.fourth.parsers.ParserException;
import org.junit.Assert;
import org.junit.Test;

public class XmlValidatorTest {
    private final static String VALID_XML = "src/main/resources/tariffs.xml";
    private final static String INVALID_XML = "src/main/resources/invalid.xml";
    private final static String XSD_FILE = "src/main/resources/tariffs.xsd";
    private final static XmlValidator validator = new XmlValidator(XSD_FILE);

    @Test
    public void testIsValidShouldReturnTrueWhenXmlIsValid() throws ParserException {
        boolean actual = validator.isValid(VALID_XML);
        Assert.assertTrue(actual);
    }

    @Test(expected = ParserException.class)
    public void testIsValidShouldThrowExceptionWhenXmlIsNotValid() throws ParserException {
        boolean actual = validator.isValid(INVALID_XML);
    }

}
