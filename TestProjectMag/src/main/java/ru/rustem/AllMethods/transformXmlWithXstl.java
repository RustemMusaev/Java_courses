package ru.rustem.AllMethods;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class transformXmlWithXstl {
    public static void transformXmlWithXstl() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        StreamSource xslStream = new StreamSource("D:\\JavaProject\\Java_courses\\TestProjectMag\\style.xslt");
        Transformer transformer = factory.newTransformer(xslStream);
        StreamSource in = new StreamSource("D:\\JavaProject\\Java_courses\\TestProjectMag\\1.xml");
        StreamResult out = new StreamResult("D:\\JavaProject\\Java_courses\\TestProjectMag\\2.xml");
        transformer.transform(in, out);
    }
}
