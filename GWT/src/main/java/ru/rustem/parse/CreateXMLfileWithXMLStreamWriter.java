package ru.rustem.parse;


import ru.rustem.model.Point;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static ru.rustem.Application.XML_FILE_TO_WRITE;

/**
 * This Class use to create xml file with used abstract XMLOutputFactory.class. Static method create new instance abstract class.
 * After create file, and open stream to formating xml file. in this stream write encoding and version value. After write root element ans using a cycle
 * write child element and his value.The end steam flush and close to finish add file.
 */
public class CreateXMLfileWithXMLStreamWriter {
    public static void createXMLfileWithXMLStreamWriter(List<Point> list) throws XMLStreamException {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlStreamWriter = null;
        try {
            xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter(XML_FILE_TO_WRITE));
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();}
            xmlStreamWriter.writeStartDocument("UTF-8","1.0");
        xmlStreamWriter.writeStartElement("points");
            for (Point i : list) {
                xmlStreamWriter.writeStartElement("point");
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
