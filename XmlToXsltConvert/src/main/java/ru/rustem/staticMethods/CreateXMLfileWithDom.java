package ru.rustem.staticMethods;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

import static ru.rustem.Program.XML_FILE_TO_WRITE;

/**
 * This Class use to create xml file with used DOM Model. Static method create Document(DOM) using DocumentBuilderFactory,
 * and add root element "entries". After for each object of list, append child element "entry". For each "entry" append
 * child element "filed" with value object of list.
 * When the output stream is terminated, used TransformerFactory for write stream to file.
 */
public class CreateXMLfileWithDom {
    public static void createXMLfileWithDom(List<Integer> list) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("entries");
            doc.appendChild(rootElement);
            for (Integer i: list) {
                Element entry = doc.createElement("entry");
                rootElement.appendChild(entry);
                Element field = doc.createElement("field");
                field.appendChild(doc.createTextNode(String.valueOf(list.get(i))));
                entry.appendChild(field);
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(XML_FILE_TO_WRITE));
            transformer.transform(source, result);
          } catch (ParserConfigurationException pce) {
            System.out.println("DocumentBuilder cannot be created which satisfies the configuration requested");
        } catch (TransformerException tfe) {
            System.out.println("an unrecoverable error occurs during the course of the transformation");
        }
    }
}
