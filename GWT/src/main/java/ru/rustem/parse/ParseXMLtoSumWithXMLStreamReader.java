package ru.rustem.parse;


import ru.rustem.model.Point;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static ru.rustem.Application.XML_FILE_TO_READ;

public class ParseXMLtoSumWithXMLStreamReader {
    public static void parseXMLtoSumWithXMLStreamReader() {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader parser = null;
        try {
            parser = xmlInputFactory.createXMLStreamReader(new FileInputStream(XML_FILE_TO_READ));
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 0;
        Point point = new Point();
        try {
            while (parser.hasNext()){
                if (parser.next() == XMLStreamConstants.START_ELEMENT){
                    if (parser.getLocalName().equals("points")){
                        while (parser.hasNext()){
                            if (parser.next() == XMLStreamConstants.START_ELEMENT){
                                if (parser.getLocalName().equals("point")){
                                    //count = count + Integer.parseInt(parser.getAttributeValue(null,"field"));
                                    point.setName(parser.getAttributeValue(null,"name"));
                                }
                            }
                        }
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        System.out.println(count+ " count");
    }
}
