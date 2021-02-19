package com.epam.task.fourth.parsers;

import com.epam.task.fourth.Director;
import com.epam.task.fourth.enums.Operator;
import com.epam.task.fourth.enums.Tariffication;
import com.epam.task.fourth.validator.XmlValidator;
import com.epam.task.fourth.entities.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public abstract class ParserTest {

    private final static String VALID_XML = "src/main/resources/tariffs.xml";
    private final static String INVALID_XML = "src/main/resources/invalid.xml";
    private final static String XSD_FILE = "src/main/resources/tariffs.xsd";
    private static final XmlValidator validator = new XmlValidator(XSD_FILE);
    private final static PensionerTariff FIRST_TARIFF = new PensionerTariff("SixtyPlus", 60, new CallPrices(1, 3, 3), 2, new Parameters(5, 20, Tariffication.TWELVESECONDS), Operator.MTS, 3);
    private final static PensionerTariff SECOND_TARIFF = new PensionerTariff("LifeStartsNow", 40, new CallPrices(4, 4, 4), 2, new Parameters(4, 14, Tariffication.ONEMINUTE), Operator.LIFE, 4);
    private final static StudentTariff THIRD_TARIFF = new StudentTariff("Chill", 70, new CallPrices(1, 2, 3), 4, new Parameters(10, 15, Tariffication.TWELVESECONDS), Operator.MTS, new Login("RayJohn7"));
    private final static StudentTariff FOURTH_TARIFF = new StudentTariff("StudyEasy", 50, new CallPrices(2, 2, 2), 1, new Parameters(3, 25, Tariffication.ONEMINUTE), Operator.A, new Login("MullerAlex7"));
    protected final static List<Tariff> EXPECTED = Arrays.asList(FIRST_TARIFF, SECOND_TARIFF, THIRD_TARIFF, FOURTH_TARIFF);
    protected static Director director = new Director(validator);

    @Test
    public void testParseShouldParseData() throws ParserException {
        List<Tariff> actual = director.parse(VALID_XML);
        Assert.assertEquals(FIRST_TARIFF, actual.get(0));
        Assert.assertEquals(SECOND_TARIFF, actual.get(1));
        Assert.assertEquals(THIRD_TARIFF, actual.get(2));
        Assert.assertEquals(FOURTH_TARIFF, actual.get(3));
        Assert.assertEquals(EXPECTED, actual);
    }

    @Test(expected = ParserException.class)
    public void testParseShouldThrowExceptionWhenDataIsNotValid() throws ParserException {
        List<Tariff> actual = director.parse(INVALID_XML);
    }
}
