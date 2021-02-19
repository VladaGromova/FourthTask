package com.epam.task.fourth.validator;

import com.epam.task.fourth.parsers.ParserException;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;

public class XmlValidator {

    private final static String VALID_XML = "XML is valid";
    private final static String EXCEPTION_MESSAGE = "Exception while validating XML";
    private final static String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private static String xsdFile;
    private final static Logger LOGGER = Logger.getLogger(XmlValidator.class);

    public XmlValidator(String xsdFile) {
        XmlValidator.xsdFile = xsdFile;
    }

    public boolean isValid(String xmlFile) throws ParserException {
        SchemaFactory factory = SchemaFactory.newInstance(LANGUAGE);
        File schemaLocation = new File(xsdFile);
        try {
            Schema schema = factory.newSchema(schemaLocation);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFile);
            validator.validate(source);
            LOGGER.info(VALID_XML);
            return true;
        } catch (SAXException | IOException e) {
            throw new ParserException(EXCEPTION_MESSAGE, e);
        }
    }
}
