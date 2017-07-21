package com.mySampleApplication.server.parse;


import com.mySampleApplication.server.model.Point;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateXMLfileWithXMLStreamWriter {
    public final String XML_FILE_TO_WRITE = "D:\\1.xml";

    public void createXMLfileWithXMLStreamWriter(List<Point> list) throws XMLStreamException {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlStreamWriter = null;
        try {
            xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter(XML_FILE_TO_WRITE));
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
        xmlStreamWriter.writeStartElement("points");
        for (Point i : list) {
            xmlStreamWriter.writeStartElement("point");
            xmlStreamWriter.writeAttribute("id", String.valueOf(i.getId()));
            xmlStreamWriter.writeStartElement("name");
            xmlStreamWriter.writeCharacters(String.valueOf(i.getName()));
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("city");
            xmlStreamWriter.writeCharacters(String.valueOf(i.getCity()));
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("country");
            xmlStreamWriter.writeCharacters(String.valueOf(i.getCountry()));
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("address");
            xmlStreamWriter.writeCharacters(String.valueOf(i.getAddress()));
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("phone");
            xmlStreamWriter.writeCharacters(String.valueOf(i.getPhone()));
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeStartElement("services");
            xmlStreamWriter.writeCharacters(String.valueOf(i.getServices()));
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndElement();
        }
        xmlStreamWriter.writeEndElement();
        xmlStreamWriter.writeEndDocument();
        xmlStreamWriter.flush();
        xmlStreamWriter.close();

    }
}
