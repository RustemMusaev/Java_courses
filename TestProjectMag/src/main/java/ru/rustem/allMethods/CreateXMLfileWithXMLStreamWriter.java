package ru.rustem.allMethods;


import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static ru.rustem.Program.XML_FILE_TO_WRITE;

public class CreateXMLfileWithXMLStreamWriter {
    public static void createXMLfileWithXMLStreamWriter(List<Integer> list) {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlStreamWriter = null;
        try {
            xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter(XML_FILE_TO_WRITE));
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            xmlStreamWriter.writeStartDocument("UTF-8","1.0");
            xmlStreamWriter.writeStartElement("entries");
            for (Integer i : list) {
                xmlStreamWriter.writeStartElement("entry");
                xmlStreamWriter.writeStartElement("field");
                xmlStreamWriter.writeCharacters(String.valueOf(i));
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeEndElement();
            }
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.flush();
            xmlStreamWriter.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

    }
}
