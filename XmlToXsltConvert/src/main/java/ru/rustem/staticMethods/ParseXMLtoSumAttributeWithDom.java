package ru.rustem.staticMethods;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import static ru.rustem.Program.XML_FILE_TO_READ;

public class ParseXMLtoSumAttributeWithDom {
    public static void parseXMLtoSumAttribute(){
        try {
            File inputFile = new File(XML_FILE_TO_READ);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("entry");
            int count = 0;
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String[] list = String.valueOf(eElement.getAttribute("field")).split(" ");
                    count = count + Integer.parseInt(list[0]);
                }
            }
            System.out.println(count);
        } catch (Exception e) {
            new RuntimeException("file not parse");
        }
    }
}
