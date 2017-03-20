package ru.rustem.allMethods;


import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static ru.rustem.Program.XML_FILE_TO_READ;

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
        try {
            while (parser.hasNext()){
                if (parser.next() == XMLStreamConstants.START_ELEMENT){
                    if (parser.getLocalName().equals("entries")){
                        while (parser.hasNext()){
                            if (parser.next() == XMLStreamConstants.START_ELEMENT){
                                if (parser.getLocalName().equals("entry")){
                                    count = count + Integer.parseInt(parser.getAttributeValue(null,"field"));
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
