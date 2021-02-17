package com.epam.task.fourth.parsers;

import com.epam.task.fourth.entities.Tariff;
import com.epam.task.fourth.entities.Tariffs;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.List;

public class JaxbParser implements Parser {

    private final static String TARIFFS_XSD = "C:\\Java Web Development\\projects\\FourthTask\\FourthTask\\src\\main\\resources\\tariffs.xsd";
    private final static String TARIFFS_XML = "C:\\Java Web Development\\projects\\FourthTask\\FourthTask\\src\\main\\resources\\tariffs.xml";

    public void jaxbParsing(){
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(Tariffs.class);
            Unmarshaller um = jc.createUnmarshaller();
            String schemaName = TARIFFS_XSD;
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(schemaName);
            Schema schema = factory.newSchema(schemaLocation);
            um.setSchema(schema);
            JAXBElement<Tariffs> root = um.unmarshal(new StreamSource(new File(TARIFFS_XML)), Tariffs.class);
            System.out.println(root);
        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
        }
    }

    public List<Tariff> parse(String file) {
        return null;
    }
}
